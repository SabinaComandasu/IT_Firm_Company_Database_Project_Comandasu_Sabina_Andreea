import Select.GenericSelect;

import DBOperations.*;

public class Main {
    public static void main(String[] args) {
        GenericSelect genericSelect = new GenericSelect();
        ClientDBOperations clientOps = new ClientDBOperations();

        //PENTRU TESTARE - OPERATII LMD:
        // - se vor sterge liniile de comment pentru insertClient, updateClient si deleteClient

        //INSERAREA UNUI CLIENT IN BAZA DE DATE
        //MAIN-UL NU ESTE RULAT TOCMAI PENTRU A SE OBSERVA OPERATIA DE INSERARE
        //DACA SE INCEARCA DIN NOU ACEEASI INSERARE, NU VA FUNCTIONA, PENTRU A NU INSERA ELEMENTE DUPLICAT
        //clientOps.insertClient("C10", "Sony Interactive Entertainment", "Entertainment & Gaming", "contact@sony.com", "0743-357-999");

        //ACTUALIZAREA UNUI CLIENT IN BAZA DE DATE
        //NU SE POATE ACTUALIZA UN CLIENT CARE NU EXISTA
        //clientOps.updateClient("C10", "Sony Interactive Entertainment", "Digital Entertainment", "contact@sony.com", "0721-247-123");

        //STERGEREA UNUI CLIENT DIN BAZA DE DATE
        //NU SE POATE STERGE UN CLIENT CARE NU EXISTA
        //clientOps.deleteClient("C10");

        //AFISAREA INFORMATIILOR UNUI CLIENT DIN BAZA DE DATE
        //VA FUNCTIONA DUPA EFECTUAREA INSERARII UNUI CLIENT
        String[] columns = {"clientID", "name", "industry", "email", "phoneNumber"};
        genericSelect.selectByID("Client", "clientID", "C10", columns);
    }
}
