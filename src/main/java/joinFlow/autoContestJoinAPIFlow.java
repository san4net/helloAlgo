package joinFlow;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class autoContestJoinAPIFlow implements Runnable {
    public String email;
    public String password;
    public String envEndpoint;
    public String teamEndpoint;
    public String roundId;
    public String tourId;
    public String userId;
    public String GQLEndpoint;

    public autoContestJoinAPIFlow(String email, String password, String envEndpoint, String GQLEndpoint, String teamEndpoint, String roundId, String tourId, String userId) {
        this.email = email;
        this.password=password;
        this.envEndpoint = envEndpoint;
        this.teamEndpoint = teamEndpoint;
        this.roundId = roundId;
        this.tourId = tourId;
        this.userId = userId;
        this.GQLEndpoint = GQLEndpoint;
    }

    @Override
    public void run(){
        try {
            login Login = new login();
            getJoinedContests getJoinedContest = new getJoinedContests();
            fetchLeagues fetchLeague = new fetchLeagues();
            isTeamCreated isteamCreated = new isTeamCreated();
            getMaxTeamId maxTeamId = new getMaxTeamId();
            getPlayersForCreateTeam getplayers = new getPlayersForCreateTeam();
            saveTeam saveteam = new saveTeam();
            joinContests join = new joinContests();
            switchTeams switchT = new switchTeams();
            String token = "";
            List<Long> joinedContests;
            Map<Long,Integer> joinedContestsThroughCode = new HashMap<>();
            AtomicInteger numberOfOperations=new AtomicInteger(0);
            Map<Long, Integer> availableContests;
            //token = Login.loginToGenToken(email, password, envEndpoint);
            token = Login.loginToGenTokenV2(email, password, GQLEndpoint);
            joinedContests = getJoinedContest.getJoinedContest(envEndpoint, token, roundId, tourId);
            availableContests = fetchLeague.getLeagues(envEndpoint, token, roundId, tourId);
            if (!isteamCreated.isTeamCreated(teamEndpoint, userId, roundId)) {
                saveteam.SaveTeam(envEndpoint, getplayers.getPlayers(envEndpoint, token, roundId, tourId), token, roundId, tourId, "1");
            }
            int maxTeams = maxTeamId.getMaxTeams(teamEndpoint, userId, roundId);
            if (maxTeams <= 2) {
                if (maxTeams <= 1) {
                    saveteam.SaveTeam(envEndpoint, getplayers.getPlayers(envEndpoint, token, roundId, tourId), token, roundId, tourId, "2");
                }
                saveteam.SaveTeam(envEndpoint, getplayers.getPlayers(envEndpoint, token, roundId, tourId), token, roundId, tourId, "3");
            }
            if(maxTeams<6){
                if(maxTeams<3)
                    saveteam.SaveTeam(envEndpoint, getplayers.getPlayers(envEndpoint, token, roundId, tourId), token, roundId, tourId, "3");
                if(maxTeams<4)
                    saveteam.SaveTeam(envEndpoint, getplayers.getPlayers(envEndpoint, token, roundId, tourId), token, roundId, tourId, "4");
                if(maxTeams<5)
                    saveteam.SaveTeam(envEndpoint, getplayers.getPlayers(envEndpoint, token, roundId, tourId), token, roundId, tourId, "5");
                if(maxTeams<6)
                    saveteam.SaveTeam(envEndpoint, getplayers.getPlayers(envEndpoint, token, roundId, tourId), token, roundId, tourId, "6");
            }
            String finalToken = token;
            AtomicInteger joinedContestCountSingle = new AtomicInteger();
            AtomicInteger joinedContestCountMultiple = new AtomicInteger();

            availableContests.entrySet().forEach(entry -> {
                if (!joinedContests.contains(entry.getKey())) {
                    if (entry.getValue() != 1) {
                        try {
                            if (join.joinContest(envEndpoint, finalToken, roundId, tourId, String.valueOf(entry.getKey()), "1")){
                                joinedContestCountSingle.getAndIncrement();
                                numberOfOperations.getAndIncrement();
                                if(joinedContestsThroughCode.containsKey(entry.getKey()))
                                    joinedContestsThroughCode.put(entry.getKey(),joinedContestsThroughCode.get(entry.getKey())+1);
                                else
                                    joinedContestsThroughCode.put(entry.getKey(),1);
                            }

                        } catch (UnirestException e) {
                            e.printStackTrace();
                        }
                    } else{
                        //join the contest with multiple teams
                        try {
                            if (join.joinContest(envEndpoint, finalToken, roundId, tourId, String.valueOf(entry.getKey()), "1")){
                                joinedContestCountMultiple.getAndIncrement();
                                numberOfOperations.getAndIncrement();
                                if(joinedContestsThroughCode.containsKey(entry.getKey()))
                                    joinedContestsThroughCode.put(entry.getKey(),joinedContestsThroughCode.get(entry.getKey())+1);
                                else
                                    joinedContestsThroughCode.put(entry.getKey(),1);
                            }
                        } catch (UnirestException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (join.joinContest(envEndpoint, finalToken, roundId, tourId, String.valueOf(entry.getKey()), "2")){
                                joinedContestCountMultiple.getAndIncrement();
                                numberOfOperations.getAndIncrement();
                                if(joinedContestsThroughCode.containsKey(entry.getKey()))
                                    joinedContestsThroughCode.put(entry.getKey(),joinedContestsThroughCode.get(entry.getKey())+1);
                                else
                                    joinedContestsThroughCode.put(entry.getKey(),1);
                            }
                        } catch (UnirestException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            System.out.println(joinedContestsThroughCode + " for user "+email);

            String finalToken1 = token;
            joinedContestsThroughCode.entrySet().forEach(entry->{
                if(entry.getValue()==1){
                    try {
                        switchT.switchteam(envEndpoint, finalToken1,roundId,String.valueOf(entry.getKey()),"4");
                        numberOfOperations.getAndIncrement();
                    } catch (UnirestException e) {
                        e.printStackTrace();
                    }
                }
                else if(entry.getValue()==2){
                    try {
                        switchT.switchteam(envEndpoint, finalToken1,roundId,String.valueOf(entry.getKey()),"4,5");
                        numberOfOperations.getAndAdd(2);
                    } catch (UnirestException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("Number of Operations: "+numberOfOperations);

        }catch (UnirestException e){
            e.printStackTrace();
        }
    }
  static class UserInfo{
        String email;
        String password;
        String userId;

      public UserInfo(String email, String password, String userId) {
          this.email = email;
          this.password = password;
          this.userId = userId;
      }

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      public String getPassword() {
          return password;
      }

      public void setPassword(String password) {
          this.password = password;
      }

      public String getUserId() {
          return userId;
      }

      public void setUserId(String userId) {
          this.userId = userId;
      }
  }

    public static void main(String args[]) {
        String envEndpoint = "api-stag.dream11.com";
        String GQLEndpoint = "stage.dream11.com";
        String teamEndpoint = "internal-team-ms-stag-int-elb-001-99857663.us-east-1.elb.amazonaws.com";
        String roundId = "13259";
        String tourId = "859";

        //UserInfo user1 = new UserInfo("b0e9df0f-87c9-4ff1-b208-0a1240dc4bc991@usersegment.com","Dream11Y","60000308");
        UserInfo user2 = new UserInfo("cee745f3-49ac-40ae-b5c5-d7ab7552946137@usersegment.com","Dream11Y","60000307");
        UserInfo user3 = new UserInfo("84f17949-f60a-47b1-aff4-e8a7530ae08555@usersegment.com","Dream11Y","60000306");
        UserInfo user4 = new UserInfo("a1ba7bf9-9e21-457e-9cff-df7f0b46e9d351@usersegment.com","Dream11Y","60000305");
        UserInfo user5 = new UserInfo("38bed35d-f914-4f6a-acda-61e3a66b32ca52@usersegment.com","Dream11Y","60000304");
        UserInfo user6 = new UserInfo("e0cc1b45-6e6a-427d-b16d-2b9c52b11e3b11@usersegment.com","Dream11Y","60000303");
        UserInfo user7 = new UserInfo("cfa05f71-9612-44ae-b030-f6434fa584f708@usersegment.com","Dream11Y","60000302");
        UserInfo user8 = new UserInfo("62b058b1-22ce-4839-b2fc-4a9b77a0796d88@usersegment.com","Dream11Y","60000301");
        UserInfo user9 = new UserInfo("10f1530d-3312-4491-9c38-fc2c1b2a2bc325@usersegment.com","Dream11Y","60000300");
        UserInfo user10 = new UserInfo("ac42f52f-1a3d-4795-ba7b-94861c1a94c812@usersegment.com","Dream11Y","60000299");
        UserInfo user11 = new UserInfo("2eb0da05-b054-459c-b3de-258f8ac539b936@usersegment.com","Dream11Y","60000298");
        UserInfo user12 = new UserInfo("d26ae689-acae-4b97-82a1-531d2a7a7eb097@usersegment.com","Dream11Y","60000297");
        UserInfo user13 = new UserInfo("901fc544-cfcc-49d8-ad97-ff560f87807130@usersegment.com","Dream11Y","60000296");
        UserInfo user14 = new UserInfo("a9fe9628-0133-4c6e-b870-b00b197219da62@usersegment.com","Dream11Y","60000295");
        UserInfo user15 = new UserInfo("93a1ec57-caa1-4c24-8d32-659d29dcc7fb18@usersegment.com","Dream11Y","60000294");
        UserInfo user16 = new UserInfo("f36e5151-8f21-4b1d-99a5-5d150c42760f66@usersegment.com","Dream11Y","60000293");
        UserInfo user17 = new UserInfo("c3acb868-a058-4446-a1ea-7569bc102c3303@usersegment.com","Dream11Y","60000292");
        UserInfo user18 = new UserInfo("67b01bec-28a5-44a8-9975-d9764f1bb52218@usersegment.com","Dream11Y","60000291");
        UserInfo user19 = new UserInfo("7dbdaedf-b564-462c-94b1-9859781114d358@usersegment.com","Dream11Y","60000290");
        UserInfo user20 = new UserInfo("3c908c6b-10b2-434c-bd83-2cc0eb3f7c8d85@usersegment.com","Dream11Y","60000289");
        UserInfo user21 = new UserInfo("a7eb0dfc-20cf-4a5a-9b3c-ba4172fa6ea551@usersegment.com","Dream11Y","60000288");
        UserInfo user22 = new UserInfo("49738694-0889-419e-98bd-f1074096921b58@usersegment.com","Dream11Y","60000287");
        UserInfo user23 = new UserInfo("3ae370d6-fcd8-4d3f-af1d-95789e1f93d855@usersegment.com","Dream11Y","60000286");
        UserInfo user24 = new UserInfo("02963ca5-c7bc-4b41-972f-2c401d173e4061@usersegment.com","Dream11Y","60000285");
        UserInfo user25 = new UserInfo("baldevAISVTC@haniLoad.com","baldev@123","60004185");
        UserInfo user26 = new UserInfo("baldevajudqi@haniLoad.com","baldev@123","60004158");
        UserInfo user27 = new UserInfo("baldevAKjnDa@haniLoad.com","baldev@123","60004118");
        UserInfo user28 = new UserInfo("baldevamoPwB@haniLoad.com","baldev@123","60004125");
        UserInfo user29 = new UserInfo("baldevarBoPo@haniLoad.com","baldev@123","60004139");
        UserInfo user30 = new UserInfo("baldevatLnMZ@haniLoad.com","baldev@123","60004113");
        UserInfo user31 = new UserInfo("baldevAZiSQr@haniLoad.com","baldev@123","60004151");
        UserInfo user32 = new UserInfo("baldevAzVYAn@haniLoad.com","baldev@123","60004145");
        UserInfo user33 = new UserInfo("baldevbqgLjG@haniLoad.com","baldev@123","60004206");
        UserInfo user34 = new UserInfo("baldevBuDRNf@haniLoad.com","baldev@123","60004175");
        UserInfo user35 = new UserInfo("baldevcYprKv@haniLoad.com","baldev@123","60004112");
        UserInfo user36 = new UserInfo("baldevdGKUFT@haniLoad.com","baldev@123","60004149");
        UserInfo user37 = new UserInfo("baldevDMFeWk@haniLoad.com","baldev@123","60004120");
        UserInfo user38 = new UserInfo("baldevdPXgZX@haniLoad.com","baldev@123","60004135");
        UserInfo user39 = new UserInfo("baldevDTPKaU@haniLoad.com","baldev@123","60004200");
        UserInfo user40 = new UserInfo("baldeveloGub@haniLoad.com","baldev@123","60004123");
        UserInfo user41 = new UserInfo("baldeveOgsuK@haniLoad.com","baldev@123","60004138");
        UserInfo user42 = new UserInfo("baldeveymfua@haniLoad.com","baldev@123","60004122");
        UserInfo user43 = new UserInfo("baldevfeOPyW@haniLoad.com","baldev@123","60004177");
        UserInfo user44 = new UserInfo("baldevFHeasu@haniLoad.com","baldev@123","60004207");
        UserInfo user45 = new UserInfo("baldevfNrmWB@haniLoad.com","baldev@123","60004146");
        UserInfo user46 = new UserInfo("baldevfOJUdX@haniLoad.com","baldev@123","60004201");
        UserInfo user47 = new UserInfo("baldevFsrNAT@haniLoad.com","baldev@123","60004119");
        UserInfo user48 = new UserInfo("baldevFxMgqu@haniLoad.com","baldev@123","60004182");
        UserInfo user49 = new UserInfo("baldevFzREJT@haniLoad.com","baldev@123","60004156");
        UserInfo user50 = new UserInfo("baldevGhfGgq@haniLoad.com","baldev@123","60004179");
        UserInfo user51 = new UserInfo("baldevgOkZRB@haniLoad.com","baldev@123","60004195");
        UserInfo user52 = new UserInfo("baldevGqwbkf@haniLoad.com","baldev@123","60004205");
        UserInfo user53 = new UserInfo("baldevgUfmnv@haniLoad.com","baldev@123","60004209");
        UserInfo user54 = new UserInfo("baldevHDlOvM@haniLoad.com","baldev@123","60004154");
        UserInfo user55 = new UserInfo("baldevhOJOzZ@haniLoad.com","baldev@123","60004165");
        UserInfo user56 = new UserInfo("baldevHXfWcz@haniLoad.com","baldev@123","60004188");
        UserInfo user57 = new UserInfo("baldeviadNot@haniLoad.com","baldev@123","60004178");
        UserInfo user58 = new UserInfo("baldevIDRkCp@haniLoad.com","baldev@123","60004131");
        UserInfo user59 = new UserInfo("baldevIEtMTY@haniLoad.com","baldev@123","60004192");
        UserInfo user60 = new UserInfo("baldevIXoNSj@haniLoad.com","baldev@123","60004130");
        UserInfo user61 = new UserInfo("baldevjjbdZk@haniLoad.com","baldev@123","60004137");
        UserInfo user62 = new UserInfo("baldevjoWxxj@haniLoad.com","baldev@123","60004198");
        UserInfo user63 = new UserInfo("baldevJrmKis@haniLoad.com","baldev@123","60004202");
        UserInfo user64 = new UserInfo("baldevJsjNQh@haniLoad.com","baldev@123","60004181");
        UserInfo user65 = new UserInfo("baldevjxpwHA@haniLoad.com","baldev@123","60004127");
        UserInfo user66 = new UserInfo("baldevKDIOOr@haniLoad.com","baldev@123","60004116");
        UserInfo user67 = new UserInfo("baldevkguDPQ@haniLoad.com","baldev@123","60004162");
        UserInfo user68 = new UserInfo("baldevLkKZsv@haniLoad.com","baldev@123","60004115");
        UserInfo user69 = new UserInfo("baldevLQXQZQ@haniLoad.com","baldev@123","60004163");
        UserInfo user70 = new UserInfo("baldevMGXryP@haniLoad.com","baldev@123","60004173");
        UserInfo user71 = new UserInfo("baldevMhUuPq@haniLoad.com","baldev@123","60004203");
        UserInfo user72 = new UserInfo("baldevNBICwS@haniLoad.com","baldev@123","60004110");
        UserInfo user73 = new UserInfo("baldevnbOYUc@haniLoad.com","baldev@123","60004124");
        UserInfo user74 = new UserInfo("baldevNepTzL@haniLoad.com","baldev@123","60004114");
        UserInfo user75 = new UserInfo("baldevnGFBdu@haniLoad.com","baldev@123","60004193");
        UserInfo user76 = new UserInfo("baldevNGJygY@haniLoad.com","baldev@123","60004168");
        UserInfo user77 = new UserInfo("baldevnGOoOc@haniLoad.com","baldev@123","60004143");
        UserInfo user78 = new UserInfo("baldevNgyXzD@haniLoad.com","baldev@123","60004204");
        UserInfo user79 = new UserInfo("baldevoduRvb@haniLoad.com","baldev@123","60004142");
        UserInfo user80 = new UserInfo("baldevofqpjx@haniLoad.com","baldev@123","60004172");
        UserInfo user81 = new UserInfo("baldevomOfsX@haniLoad.com","baldev@123","60004208");
        UserInfo user82 = new UserInfo("baldevORPqeO@haniLoad.com","baldev@123","60004147");
        UserInfo user83 = new UserInfo("baldevouIAIL@haniLoad.com","baldev@123","60004159");
        UserInfo user84 = new UserInfo("baldevouwDNb@haniLoad.com","baldev@123","60004128");
        UserInfo user85 = new UserInfo("baldevoVfQLT@haniLoad.com","baldev@123","60004126");
        UserInfo user86 = new UserInfo("baldevpMENBH@haniLoad.com","baldev@123","60004144");
        UserInfo user87 = new UserInfo("baldevPmoNHX@haniLoad.com","baldev@123","60004184");
        UserInfo user88 = new UserInfo("baldevpwromP@haniLoad.com","baldev@123","60004164");
        UserInfo user89 = new UserInfo("baldevPXozGl@haniLoad.com","baldev@123","60004157");
        UserInfo user90 = new UserInfo("baldevPYVhtH@haniLoad.com","baldev@123","60004183");
        UserInfo user91 = new UserInfo("baldevqiDZmG@haniLoad.com","baldev@123","60004160");
        UserInfo user92 = new UserInfo("baldevqikuKd@haniLoad.com","baldev@123","60004152");
        UserInfo user93 = new UserInfo("baldevqskkMU@haniLoad.com","baldev@123","60004150");
        UserInfo user94 = new UserInfo("baldevqSVRTn@haniLoad.com","baldev@123","60004196");
        UserInfo user95 = new UserInfo("baldevReccmm@haniLoad.com","baldev@123","60004136");
        UserInfo user96 = new UserInfo("baldevRfhbhM@haniLoad.com","baldev@123","60004111");
        UserInfo user97 = new UserInfo("baldevrlFaPP@haniLoad.com","baldev@123","60004148");
        UserInfo user98 = new UserInfo("baldevsBoxWG@haniLoad.com","baldev@123","60004132");
        UserInfo user99 = new UserInfo("baldevSHVqGQ@haniLoad.com","baldev@123","60004169");
        UserInfo user100 = new UserInfo("baldevsRjmWA@haniLoad.com","baldev@123","60004199");
        UserInfo user101 = new UserInfo("baldevtlGmNG@haniLoad.com","baldev@123","60004187");
        UserInfo user102 = new UserInfo("baldevtmsoNR@haniLoad.com","baldev@123","60004180");
        UserInfo user103 = new UserInfo("baldevtRujgY@haniLoad.com","baldev@123","60004170");
        UserInfo user104 = new UserInfo("baldevTzKyAG@haniLoad.com","baldev@123","60004117");
        UserInfo user105 = new UserInfo("baldevuFAlIV@haniLoad.com","baldev@123","60004176");
        UserInfo user106 = new UserInfo("baldevUqeoQB@haniLoad.com","baldev@123","60004167");
        UserInfo user107 = new UserInfo("baldevvILKNr@haniLoad.com","baldev@123","60004174");
        UserInfo user108 = new UserInfo("baldevvrPyaK@haniLoad.com","baldev@123","60004153");
        UserInfo user109 = new UserInfo("baldevWemaUt@haniLoad.com","baldev@123","60004140");
        UserInfo user110 = new UserInfo("baldevWJZDbv@haniLoad.com","baldev@123","60004141");
        UserInfo user111 = new UserInfo("baldevWluUYi@haniLoad.com","baldev@123","60004129");
        UserInfo user112 = new UserInfo("baldevWlZCAQ@haniLoad.com","baldev@123","60004194");
        UserInfo user113 = new UserInfo("baldevwQhypB@haniLoad.com","baldev@123","60004186");
        UserInfo user114 = new UserInfo("baldevWQnVCC@haniLoad.com","baldev@123","60004166");
        UserInfo user115 = new UserInfo("baldevwsnwhz@haniLoad.com","baldev@123","60004191");
        UserInfo user116 = new UserInfo("baldevxPvmrO@haniLoad.com","baldev@123","60004190");
        UserInfo user117 = new UserInfo("baldevxuwOPC@haniLoad.com","baldev@123","60004197");
        UserInfo user118 = new UserInfo("baldevyCMMVH@haniLoad.com","baldev@123","60004134");
        UserInfo user119 = new UserInfo("baldevYdhFUw@haniLoad.com","baldev@123","60004210");
        UserInfo user120 = new UserInfo("baldevydpRnI@haniLoad.com","baldev@123","60004121");
        UserInfo user121 = new UserInfo("baldevYfnEFw@haniLoad.com","baldev@123","60004161");
        UserInfo user122 = new UserInfo("baldevYSHOZf@haniLoad.com","baldev@123","60004155");
        UserInfo user123 = new UserInfo("baldevzcFFhM@haniLoad.com","baldev@123","60004133");
        UserInfo user124 = new UserInfo("baldevZDNnyP@haniLoad.com","baldev@123","60004171");
        UserInfo user125 = new UserInfo("baldevZiCeaA@haniLoad.com","baldev@123","60004189");

        Set<UserInfo> users = new HashSet<>();
        //users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);
        users.add(user13);
        users.add(user14);
        users.add(user15);
        users.add(user16);
        users.add(user17);
        users.add(user18);
        users.add(user19);
        users.add(user20);
        users.add(user21);
        users.add(user22);
        users.add(user23);
        users.add(user24);
        users.add(user25);
        users.add(user26);
        users.add(user27);
        users.add(user28);
        users.add(user29);
        users.add(user30);
        users.add(user31);
        users.add(user32);
        users.add(user33);
        users.add(user34);
        users.add(user35);
        users.add(user36);
        users.add(user37);
        users.add(user38);
        users.add(user39);
        users.add(user40);
        users.add(user41);
        users.add(user42);
        users.add(user43);
        users.add(user44);
        users.add(user45);
        users.add(user46);
        users.add(user47);
        users.add(user48);
        users.add(user49);
        users.add(user50);
        users.add(user51);
        users.add(user52);
        users.add(user53);
        users.add(user54);
        users.add(user55);
        users.add(user56);
        users.add(user57);
        users.add(user58);
        users.add(user59);
        users.add(user60);
        users.add(user61);
        users.add(user62);
        users.add(user63);
        users.add(user64);
        users.add(user65);
        users.add(user66);
        users.add(user67);
        users.add(user68);
        users.add(user69);
        users.add(user70);
        users.add(user71);
        users.add(user72);
        users.add(user73);
        users.add(user74);
        users.add(user75);
        users.add(user76);
        users.add(user77);
        users.add(user78);
        users.add(user79);
        users.add(user80);
        users.add(user81);
        users.add(user82);
        users.add(user83);
        users.add(user84);
        users.add(user85);
        users.add(user86);
        users.add(user87);
        users.add(user88);
        users.add(user89);
        users.add(user90);
        users.add(user91);
        users.add(user92);
        users.add(user93);
        users.add(user94);
        users.add(user95);
        users.add(user96);
        users.add(user97);
        users.add(user98);
        users.add(user99);
        users.add(user100);
        users.add(user101);
        users.add(user102);
        users.add(user103);
        users.add(user104);
        users.add(user105);
        users.add(user106);
        users.add(user107);
        users.add(user108);
        users.add(user109);
        users.add(user110);
        users.add(user111);
        users.add(user112);
        users.add(user113);
        users.add(user114);
        users.add(user115);
        users.add(user116);
        users.add(user117);
        users.add(user118);
        users.add(user119);
        users.add(user120);
        users.add(user121);
        users.add(user122);
        users.add(user123);
        users.add(user124);
        users.add(user125);
        for(int i=0;i<20;i++) {
            //change stream() to parallelStream
            users.stream().forEach(usr ->
                    new autoContestJoinAPIFlow(usr.email, usr.password, envEndpoint, GQLEndpoint, teamEndpoint, roundId, tourId, usr.userId).run()
            );
            System.out.println("Complete Iteration: "+i);
        }
    }
}