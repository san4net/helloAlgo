package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class getJoinedContests {
    public List<Long> getJoinedContest(String envEndpoint, String accessToken,String roundId,String tourId) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/getjoinedleagues/v1")
                .header("Authorization", "Bearer "+accessToken)
                .header("Content-Type", "application/json")
                .body("{\"roundId\":\""+roundId+"\",\"siteId\":\"1\",\"tourId\":\""+tourId+"\",\"version\":\"785\"}")
                .asString();
        JSONObject joinedContestResponse= new JSONObject(response.getBody());
        JSONArray joinedContests = new JSONArray(joinedContestResponse.getJSONArray("Leagues").toString());
        List<Long> joined = new ArrayList<>();
        for(int i=0;i<joinedContests.length();i++){
            joined.add(joinedContests.getJSONObject(i).getLong("LeagueId"));
        }
        return joined;
    }
}
