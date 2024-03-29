package viewseconda.agency;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripStatusBean;
import com.ispw.progetto.controller_app.TripStatusController;
import com.ispw.progetto.exception.NotValidCouponException;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripStatusCLI {
    protected final AgencyBean currentAgency;

    public TripStatusCLI(AgencyBean currentAgency){this.currentAgency=currentAgency;}

    public void start(HomeAgencyCLI home,int id) throws SQLException, IOException, NotValidCouponException {

        String username;
        boolean state;

        List<TripStatusBean> stati = TripStatusController.showtripstatus(id);
        for (TripStatusBean stato : stati){
            username = stato.getUsername();
            state = stato.isStatus();
            Printer.printMessage("_________________________");
            Printer.printMessage("Username: "+username);
            Printer.printMessage("Stato: "+state);
        }

        Scanner scanner = new Scanner(System.in);
        Printer.printMessage("Inserire lo username di cui confermare la prenotazione (non scrivere nulla se non si desidera fare nulla): ");
        String user = scanner.nextLine();
        if(user.isEmpty()){
            home.start();
        } //verificare se sia meglio sostituire questo if con un ciclo while
        else{
            TripStatusController statusupdater = new TripStatusController();
            boolean b = statusupdater.updatetripstatus(id,user);
            if (b){
                Printer.printMessage("Stato aggiornato");
                start(home,id);
            }
            else{
                Printer.printMessage("Query non eseguita");
                home.start();
            }
        }



    }
}
