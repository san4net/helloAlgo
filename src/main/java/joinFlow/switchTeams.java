package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class switchTeams {
    public boolean switchteam(String envEndpoint,String accessToken,String roundId,String contestId,String teamId) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/updateteamsinleague/v1")
                .header("Authorization", "Bearer "+accessToken)
                .header("Content-Type", "application/json")
                .header("siteId", "1")
                .body("{\"teamIds\":["+teamId+"],\"roundId\":\""+roundId+"\",\"leagueId\":\""+contestId+"\"}")
                .asString();
        if(response.getStatus()==200){
            return true;
        }
        return false;
    }
}
