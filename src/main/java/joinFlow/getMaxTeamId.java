package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class getMaxTeamId {
    public int getMaxTeams(String teamEndPoint,String userId,String roundId) throws UnirestException{
        HttpResponse<String> response = Unirest.get("http://"+teamEndPoint+"/maxteamid?userId="+userId+"&roundId="+roundId)
                .asString();
        JSONObject res = new JSONObject(response.getBody());
        return res.getInt("maxTeamId");
    }
}
