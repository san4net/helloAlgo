package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

public class joinContests {


    public boolean joinContest(String envEndpoint,String accessToken,String roundId,String tourId,String contestId,String teamId) throws UnirestException, JSONException {
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/joinleague/v4")
                .header("Authorization", "Bearer "+accessToken)
                .header("Content-Type", "application/json")
                .header("siteId", "1")
                .body("{\"teamId\":\""+teamId+"\",\"insertFlg\":1,\"device\":\"ios\",\"roundId\":\""+roundId+"\",\"tourId\":\""+tourId+"\",\"leagueId\":\""+contestId+"\"}")
                .asString();
        JSONObject responseBody = new JSONObject(response.getBody());
        try {
            if (responseBody.getJSONObject("msg").getString("MsgCode").equalsIgnoreCase("MG455")) {
                System.out.println("Using queue for " + contestId);
                response = Unirest.post("https://" + envEndpoint + "/joinleague/v3")
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Content-Type", "application/json")
                        .header("siteId", "1")
                        .body("{\"teamId\":\"" + teamId + "\",\"insertFlg\":1,\"device\":\"ios\",\"roundId\":\"" + roundId + "\",\"tourId\":\"" + tourId + "\",\"leagueId\":\"" + contestId + "\",\"useContestQueue\":true}")
                        .asString();
            }
            responseBody = new JSONObject(response.getBody());
            if (responseBody.getJSONObject("msg").getString("MsgCode").equalsIgnoreCase("MG074") || responseBody.getJSONObject("msg").getString("MsgCode").equalsIgnoreCase("MG010G")) {
                return true;
            } else {
                System.out.println(responseBody.getJSONObject("msg").getString("MsgCode") + " For league " + contestId);
                if (responseBody.getJSONObject("msg").getString("MsgCode").equalsIgnoreCase("MG070")) {
                    System.out.println(contestId + " : " + responseBody.getJSONObject("msg").getString("MsgCode") + " for league " + contestId);
                }
            }
        }catch (Exception e){
            System.out.println(responseBody);
        }
        return false;
    }


}
