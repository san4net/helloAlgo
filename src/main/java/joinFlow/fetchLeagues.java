package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fetchLeagues {
    public Map<Long,Integer> getLeagues(String envEndpoint, String accessToken, String roundId, String tourId) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/fetchleagues/v4")
                .header("Authorization", "Bearer "+accessToken)
                .header("Content-Type", "application/json")
                .body("{\"roundId\":\""+roundId+"\",\"siteId\":\"1\",\"tourId\":\""+tourId+"\",\"device\":\"ios\"}")
                .asString();
        Map<Long,Integer> contests = new HashMap<>();
        JSONObject fetchLeaguesResponse = new JSONObject(response.getBody());
        for(int i=0;i<fetchLeaguesResponse.getJSONArray("sections").length();i++){
            for(int j=0;j<fetchLeaguesResponse.getJSONArray("sections").getJSONObject(i).getJSONArray("leagues").length();j++){
                contests.put(fetchLeaguesResponse.getJSONArray("sections").getJSONObject(i).getJSONArray("leagues").getJSONObject(j).getLong("LeagueId"),fetchLeaguesResponse.getJSONArray("sections").getJSONObject(i).getJSONArray("leagues").getJSONObject(j).getInt("MultipleEntry"));
            }
        }
        return contests;
    }

    public Map<Long,Map<Integer,Integer>> getLeaguesWithSizes(String envEndpoint, String accessToken, String roundId, String tourId) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/fetchleagues/v4")
                .header("Authorization", "Bearer "+accessToken)
                .header("Content-Type", "application/json")
                .body("{\"roundId\":\""+roundId+"\",\"siteId\":\"1\",\"tourId\":\""+tourId+"\",\"device\":\"ios\"}")
                .asString();
        Map<Long,Map<Integer,Integer>> contests = new HashMap<>();
        JSONObject fetchLeaguesResponse = new JSONObject(response.getBody());
        for(int i=0;i<fetchLeaguesResponse.getJSONArray("sections").length();i++){
            for(int j=0;j<fetchLeaguesResponse.getJSONArray("sections").getJSONObject(i).getJSONArray("leagues").length();j++){
                int finalI = i;
                int finalJ = j;
                contests.put(fetchLeaguesResponse.getJSONArray("sections").getJSONObject(i).getJSONArray("leagues").getJSONObject(j).getLong("LeagueId"),new HashMap(){{put(fetchLeaguesResponse.getJSONArray("sections").getJSONObject(finalI).getJSONArray("leagues").getJSONObject(finalJ).getInt("MultipleEntry"),fetchLeaguesResponse.getJSONArray("sections").getJSONObject(finalI).getJSONArray("leagues").getJSONObject(finalJ).getInt("MultipleEntry"));}});
            }
        }
        return contests;
    }
}
