package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class getPlayersForCreateTeam {
    public List<Integer> getPlayers(String envEndpoint, String accessToken, String roundId, String tourId) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://"+envEndpoint+"/roundplayers/v1?roundId="+roundId+"&teamId=1&tourId="+tourId)
                .header("Authorization", "Bearer "+accessToken)
                .header("siteId", "1")
                .asString();

        String teamName1;
        String teamName2;
        Map<String,Integer> postionNumber = new HashMap<>();
        List<Integer> playersToReturn= new ArrayList<>();

        postionNumber.put("WK",1);
        postionNumber.put("BAT",4);
        postionNumber.put("ALL",2);
        postionNumber.put("BOWL",4);

        JSONObject responsePlayers = new JSONObject(response.getBody());
        teamName1=responsePlayers.getJSONArray("Squads").getJSONObject(0).getString("SquadName");
        teamName2=responsePlayers.getJSONArray("Squads").getJSONObject(1).getString("SquadName");
        AtomicInteger team1count= new AtomicInteger();
        AtomicInteger team2count=new AtomicInteger();
        AtomicInteger wkCount= new AtomicInteger();
        AtomicInteger batCount=new AtomicInteger();
        AtomicInteger allCount=new AtomicInteger();
        AtomicInteger bowlCount=new AtomicInteger();


        JSONObject playersObject = new JSONObject(responsePlayers.getJSONObject("Players").toString());
        String finalTeamName1 = teamName1;
        String finalTeamName2 = teamName2;
        postionNumber.entrySet().forEach(entry -> {
            for(int i=0;i<playersObject.getJSONArray(entry.getKey()).length();i++){
                if(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getFloat("PlayerPrice")<=9){
                    if(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getString("SquadName").equalsIgnoreCase(finalTeamName1)){
                        if(team1count.intValue()<=6){
                            if(entry.getKey().equalsIgnoreCase("WK") && wkCount.intValue() <entry.getValue()){
                                team1count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                wkCount.getAndIncrement();
                            }
                            else if(entry.getKey().equalsIgnoreCase("BAT") && batCount.intValue() <entry.getValue()){
                                team1count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                batCount.getAndIncrement();
                            }
                            else if(entry.getKey().equalsIgnoreCase("ALL") && allCount.intValue() <entry.getValue()){
                                team1count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                allCount.getAndIncrement();
                            }
                            else if(entry.getKey().equalsIgnoreCase("BOWL") && bowlCount.intValue() <entry.getValue()){
                                team1count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                bowlCount.getAndIncrement();
                            }
                        }
                    } else if(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getString("SquadName").equalsIgnoreCase(finalTeamName2)){
                        if(team2count.intValue()<=6){
                            if(entry.getKey().equalsIgnoreCase("WK") && wkCount.intValue() <entry.getValue()){
                                team2count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                wkCount.getAndIncrement();
                            }
                            else if(entry.getKey().equalsIgnoreCase("BAT") && batCount.intValue() <entry.getValue()){
                                team2count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                batCount.getAndIncrement();
                            }
                            else if(entry.getKey().equalsIgnoreCase("ALL") && allCount.intValue() <entry.getValue()){
                                team2count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                allCount.getAndIncrement();
                            }
                            else if(entry.getKey().equalsIgnoreCase("BOWL") && bowlCount.intValue() <entry.getValue()){
                                team2count.getAndIncrement();
                                playersToReturn.add(playersObject.getJSONArray(entry.getKey()).getJSONObject(i).getInt("PlayerId"));
                                bowlCount.getAndIncrement();
                            }
                        }
                    }
                }
            }
        });
        return playersToReturn;
    }
}
