package hyper;
import java.sql.*;

public class Connect {

    public static String appostrophe(String chaine){
        StringBuffer strbuf;

        strbuf = new StringBuffer(chaine);
        int k = 0;

        if(chaine.contains("'")){
            for (int i = 0; i < chaine.length() ; i++){
                if (chaine.charAt(i) == '\''){
                    strbuf.insert(i + k,'\'');
                    k++;

                }
            }
        }
        else{
            return chaine;
        }
        return strbuf.toString();

    }
    public static void main(String[] args) {



        String DB_URL_HyperFile = "jdbc:odbc:local" ;
        //informations necessaires pour accèder à la base de données MySQL
        String DB_URL_MY = "jdbc:mysql://localhost/logiteg?autoReconnect=true&useSSL=false";
        String DB_USER_MY = "root";
        String DB_PASS_MY = "";

        try {
            //Connexion à la bd HyperFile
            Connection ContactHyperFile = DriverManager.getConnection(DB_URL_HyperFile,"admin","");
            System.out.println("connected to hfsql");
            Statement jRequeteHyperFileRAY = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileFAM = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileSFAM = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileREF = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileDEF = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileCOND = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileTAILLE = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileCOULEUR = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileTVA = ContactHyperFile.createStatement();
            Statement jRequeteHyperFilePHT = ContactHyperFile.createStatement();
            Statement jRequeteHyperFileTTC = ContactHyperFile.createStatement();

            //Connexion à la bd MySQL

            Connection ContactMy = DriverManager.getConnection(DB_URL_MY,DB_USER_MY,DB_PASS_MY);
            System.out.println("connected to MySQL");
            Statement jRequeteMY = ContactMy.createStatement();






            // Récuperation et insertion
            ResultSet ResultatHyperFileRAY = jRequeteHyperFileRAY.executeQuery("SELECT IDRAY FROM article");
            ResultSet ResultatHyperFileFAM = jRequeteHyperFileFAM.executeQuery("SELECT IDFAM FROM article");
            ResultSet ResultatHyperFileSFAM = jRequeteHyperFileSFAM.executeQuery("SELECT IDSSFAM FROM article");
            ResultSet ResultatHyperFileREF = jRequeteHyperFileREF.executeQuery("SELECT Reference FROM article");
            ResultSet ResultatHyperFileDEF = jRequeteHyperFileDEF.executeQuery("SELECT DEF FROM article");
            ResultSet ResultatHyperFileCOND = jRequeteHyperFileCOND.executeQuery("SELECT N_UNITE FROM article");
            ResultSet ResultatHyperFileTAILLE = jRequeteHyperFileTAILLE.executeQuery("SELECT IDTAILLE FROM article");
            ResultSet ResultatHyperFileCOULEUR = jRequeteHyperFileCOULEUR.executeQuery("SELECT IDCOULEUR FROM article");
            ResultSet ResultatHyperFileTVA = jRequeteHyperFileTVA.executeQuery("SELECT IDCODE_TVA FROM article");
            ResultSet ResultatHyperFilePHT = jRequeteHyperFilePHT.executeQuery("SELECT Prix_HT FROM article");
            ResultSet ResultatHyperFileTTC = jRequeteHyperFileTTC.executeQuery("SELECT PRIX_TTC FROM article");


            //ResultSetMetaData jMetaData= ResultatHyperFile.getMetaData();
            String transitoire1 ;
            String transitoire2;
            String transitoire3 ;
            String transitoire4 ;
            String transitoire5 ;
            String transitoire6 ;
            String transitoire7 ;
            String transitoire8 ;
            String transitoire9 ;
            String transitoire10 ;



            for (int i=0;i<2200;i++){

                ResultatHyperFileRAY.next();
                ResultatHyperFileFAM.next();
                ResultatHyperFileSFAM.next();
                ResultatHyperFileREF.next();
                ResultatHyperFileDEF.next();
                ResultatHyperFileCOND.next();
                ResultatHyperFileTAILLE.next();
                ResultatHyperFileCOULEUR.next();
                ResultatHyperFileTVA.next();
                ResultatHyperFilePHT.next();
                ResultatHyperFileTTC.next();



                transitoire1 = ResultatHyperFileRAY.getString(1);
                transitoire2 = ResultatHyperFileFAM.getString(1);
                transitoire3 = ResultatHyperFileSFAM.getString(1);
                transitoire4 = ResultatHyperFileDEF.getString(1);
                transitoire5 = ResultatHyperFileREF.getString(1);
                //transitoire6 = ResultatHyperFileDEF.getString(1);
                //transitoire7 = ResultatHyperFileDEF.getString(1);
                //transitoire8 = ResultatHyperFileDEF.getString(1);
                //transitoire9 = ResultatHyperFileDEF.getString(1);

                jRequeteMY.executeUpdate("INSERT INTO article (RAYON,FAMILLE,SousFamille,Référence,Definition ,Conditionnement,Taille,Couleur,TVA,TarifHT,TarifTTC) VALUES ( ' " +
                        appostrophe(transitoire1)  + " ' ,' "+ appostrophe(transitoire2) + "' ,'" +
                        appostrophe(transitoire3) + "' ,'" + appostrophe(transitoire5) +  "' ,'" +
                        appostrophe(transitoire4)  + "' ,'" +  ResultatHyperFileCOND.getString(1) +
                        "' ,'" + ResultatHyperFileTAILLE.getString(1) +"' ,'" +
                        ResultatHyperFileCOULEUR.getString(1) + "' ,'" + ResultatHyperFileTVA.getString(1)
                         + "' ,'" +  ResultatHyperFilePHT.getString(1)+"' ,'" + ResultatHyperFileTTC.getString(1)+ "') ");


            }



        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

}