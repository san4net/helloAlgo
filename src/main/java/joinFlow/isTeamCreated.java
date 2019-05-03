package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class isTeamCreated {
    public boolean isTeamCreated(String teamEndPoint,String userId,String roundId) throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://"+teamEndPoint+"/isteamcreated?userId="+userId+"&roundId="+roundId)
                .asString();
        JSONObject res = new JSONObject(response.getBody());
        if(res.getBoolean("isTeamCreated"))
            return true;
        else
            return false;
    }
}
