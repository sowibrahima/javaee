package MiddleWares;

import Model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseMiddleWare {

    EntityManagerFactory emf;
    EntityManager em;

    public DatabaseMiddleWare(){
        emf = Persistence.createEntityManagerFactory("capteurdb");
        em = emf.createEntityManager();
    }

    private void beginTransaction() {
        em.getTransaction().begin();
    }

    private void endTransaction() {
        em.getTransaction().commit();
        //em.close();
    }

    public  List<CapteurdataEntity> getCapteurDataByCapteurId(int capteur_id){
        List<CapteurdataEntity> capteurdatas = new ArrayList<>();

        beginTransaction();
        capteurdatas = em.createQuery("SELECT p FROM CapteurdataEntity p where p.idCapteur =" + String.valueOf(capteur_id)).getResultList();

       endTransaction();
       return capteurdatas;
    }

    public Capteur2 getCapteur2byId(int search_id){

        Capteur2 result = null;

        beginTransaction();

        List<CapteurEntity> capteurs =  em.createQuery("SELECT p FROM CapteurEntity p where p.id =" + String.valueOf(search_id)).getResultList();
        if(capteurs.size() != 1){
            endTransaction();
            return result;
        }else{
            CapteurEntity capteurEntity = capteurs.get(0);
            endTransaction();

            beginTransaction();
            List<VilleEntity> villes = em.createQuery("SELECT p FROM VilleEntity p where p.id =" + capteurEntity.getIdVille()).getResultList();
            if(villes.size() != 1){
                endTransaction();
                return result;
            }

            VilleEntity villeEntity = villes.get(0);

            endTransaction();

            beginTransaction();
            List<PaysEntity> paysEntities = em.createQuery("SELECT p FROM PaysEntity p where p.id =" + villeEntity.getIdPays()).getResultList();
            if(paysEntities.size() != 1){
                endTransaction();
                return result;
            }
            PaysEntity paysEntity = paysEntities.get(0);

            endTransaction();

            result = new Capteur2(capteurEntity,paysEntity,villeEntity);
        }

        return result;
    }

    public List<Capteur2> getCapteur2byIds(List<Integer> ids){
        List<Capteur2> result= new ArrayList<>();
        for(Integer id : ids){
            result.add(getCapteur2byId(id));
        }
        return result;
    }

    public List<Capteur2> getAllCapteur2(){

        List<Capteur2> result= new ArrayList<>();


        beginTransaction();
        List<CapteurEntity> capteurs = em.createQuery("SELECT p FROM CapteurEntity p").getResultList();
        endTransaction();

        for(CapteurEntity capteurEntity :capteurs){
            result.add(getCapteur2byId(capteurEntity.getId()));
        }

        return result;
    }

    public boolean userInDb(String username, String password) {

        beginTransaction();
        List<IdentifiantEntity> identifiantsEntities = em.createQuery("SELECT p FROM IdentifiantEntity p").getResultList();
        endTransaction();

        for(IdentifiantEntity identifiantsEntity : identifiantsEntities){
            if(identifiantsEntity.getUser().equals(username) && identifiantsEntity.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}