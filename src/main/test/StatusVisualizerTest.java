import com.ispw.progetto.bean.TripStatusBean;
import com.ispw.progetto.controller_app.TripStatusController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusVisualizerTest {
    /*
    * Author of the test: Podda Alessandro
    * MATRICOLA: 0316851
    * */


    @Test
    public void statusvisualizertest() throws SQLException, IOException {
        int id =2;
        String username=null;
        boolean state=false;
        String testusername="lucaaaa02";
        boolean teststate=false;
        List<TripStatusBean> stati = TripStatusController.showtripstatus(id);
        for (TripStatusBean stato : stati) {
            username = stato.getUsername();
            state = stato.isStatus();
        }
        assertEquals(username,testusername);
        assertEquals(state,teststate);
    }
}