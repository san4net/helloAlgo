package joinFlow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class login {
    public String loginToGenToken(String email,String password,String envEndpoint) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/login/v1")
                .header("Content-Type", "application/json")
                .body("{\"email\":\""+email+"\",\"password\":\""+password+"\",\"siteId\":\"1\"}")
                .asString();
        JSONObject loginResponse= new JSONObject(response.getBody());
        String accessToken=loginResponse.getString("accessToken");
        return accessToken;
    }

    public String loginToGenTokenV2(String email,String password,String envEndpoint) throws UnirestException {
        String userIdentifier = checkCredReq(email,password,envEndpoint);
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/graphql/mutation/ios/login-two-factor-verify-otp")
                .header("Content-Type", "application/json")
                .body("{\"variables\": {\"OTP\": \"123123\",\"userIdentifier\": \""+userIdentifier+"\"},\"query\": \"mutation LoginTwoFactorVerifyOTP($userIdentifier: String!, $OTP: String!) { loginTwoFactorVerifyOTP(userIdentifier: $userIdentifier, otp: $OTP) {__typename userId accessToken refreshToken message}}\"}")
                .asString();
        JSONObject loginResponse= new JSONObject(response.getBody());
        String accessToken=loginResponse.getJSONObject("data").getJSONObject("loginTwoFactorVerifyOTP").getString("accessToken");
        return accessToken;
    }

    public String checkCredReq(String email,String password,String envEndpoint) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://"+envEndpoint+"/graphql/mutation/ios/login-two-factor-check-credentials")
                .header("Content-Type", "application/json")
                .body("{\"variables\": {\"password\": \""+password+"\",\"email\": \""+email+"\"},\"query\": \"mutation LoginTwoFactorCheckCredentials($email: String!, $password: String!) { loginTwoFactorCheckCredentials(email: $email, password: $password, sendOTP: true) { __typename userIdentifier isMobileVerified otpResponse { __typename ...GOtpResponse } }}fragment GOtpResponse on OtpResponse { __typename userIdentifier remainingResendCount retryAfter message successful otpAttemptCount resendButtonActive maskedMobileNumber}\"}")
                .asString();
        JSONObject checkCredReqResponse= new JSONObject(response.getBody());
        String userIdentifier = checkCredReqResponse.getJSONObject("data").getJSONObject("loginTwoFactorCheckCredentials").getString("userIdentifier");
        return userIdentifier;
    }
}
