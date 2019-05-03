package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;
import java.util.Random;

public class saveTeam {
    public boolean SaveTeam(String envEndpoint, List<Integer> players,String accessToken,String roundId,String tourId,String teamId) throws UnirestException {
        String playerListAsString = "";
        for(int i=0;i<players.size();i++){
            playerListAsString = playerListAsString+String.valueOf(players.get(i))+",";
        }
        if(playerListAsString.charAt(playerListAsString.length()-1)==','){
            playerListAsString=playerListAsString.substring(0,playerListAsString.length()-1);
        }
        Random rand = new Random();
        int randomindex = rand.nextInt(10);
        String captain = String.valueOf(players.get(randomindex));
        String vc="";
        if(randomindex<10)
            vc = String.valueOf(players.get(randomindex+1));
        else
            vc = String.valueOf(players.get(randomindex-1));
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/saveteam/v1")
                .header("Authorization", "Bearer "+accessToken)
                .header("Content-Type", "application/json")
                .body("{\"roundId\":\""+roundId+"\",\"userTeamId\":\""+teamId+"\",\"players\":\""+playerListAsString+"\",\"siteId\":\"1\",\"tourId\":\""+tourId+"\",\"captainId\":\""+captain+"\",\"version\":\"785\",\"vcID\":\""+vc+"\",\"device\":\"ios\"}")
                .asString();
        if(response.getStatus()==200)
            return true;
        else{
            rand = new Random();
            randomindex = rand.nextInt(10);
            captain = String.valueOf(players.get(randomindex));
            if(randomindex<10)
                vc = String.valueOf(players.get(randomindex+1));
            else
                vc = String.valueOf(players.get(randomindex-1));
            response = Unirest.post("https://"+envEndpoint+"/saveteam/v1")
                    .header("Authorization", "Bearer "+accessToken)
                    .header("Content-Type", "application/json")
                    .body("{\"roundId\":\""+roundId+"\",\"userTeamId\":\""+teamId+"\",\"players\":\""+playerListAsString+"\",\"siteId\":\"1\",\"tourId\":\""+tourId+"\",\"captainId\":\""+captain+"\",\"version\":\"785\",\"vcID\":\""+vc+"\",\"device\":\"ios\"}")
                    .asString();
            if(response.getStatus()==200)
                return true;
            else
            {
                System.out.println("COULD NOT CREATE TEAM. CONTEST JOIN FLOW WILL FAIL");
                return false;
            }
        }
    }
}
