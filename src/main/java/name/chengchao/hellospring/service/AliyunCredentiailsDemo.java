package name.chengchao.hellospring.service;

import com.alibaba.fastjson2.JSON;
import com.aliyun.sts20150401.Client;
import com.aliyun.sts20150401.models.GetCallerIdentityResponse;

public class AliyunCredentiailsDemo {
    public static void main(String[] args) throws Exception {
        String result = test_AK();
        System.out.println(result);
    }

    // 使用明文AK
    public static String test_AK() throws Exception {
        com.aliyun.credentials.models.Config credentialsConfig = new com.aliyun.credentials.models.Config();
        credentialsConfig.setType("access_key");
        credentialsConfig.setAccessKeyId("LTAI5t67fSzGxdoscRevULiy");
        credentialsConfig.setAccessKeySecret("VC77xATXD16QEZR1xRgkEMtY6W51Dy");
        com.aliyun.credentials.Client credentialClient = new com.aliyun.credentials.Client(credentialsConfig);
        com.aliyun.teaopenapi.models.Config stsConfig = new com.aliyun.teaopenapi.models.Config();
        stsConfig.setEndpoint("sts.cn-hangzhou.aliyuncs.com");
        stsConfig.setCredential(credentialClient);
        Client stsClient = new Client(stsConfig);
        GetCallerIdentityResponse callerIdentityResponse = stsClient.getCallerIdentity();
        return JSON.toJSONString(callerIdentityResponse.getBody());
    }

    // ack的RRSA
    public static String test_oidc_role_k8s() throws Exception {
        com.aliyun.credentials.models.Config config = new com.aliyun.credentials.models.Config();
        config.setType("oidc_role_arn");
        config.setRoleSessionName("charlesSessionName");

        com.aliyun.credentials.Client credentialClient = new com.aliyun.credentials.Client(config);
        com.aliyun.teaopenapi.models.Config stsConfig = new com.aliyun.teaopenapi.models.Config();
        stsConfig.setEndpoint("sts.cn-hangzhou.aliyuncs.com");
        stsConfig.setCredential(credentialClient);
        Client stsClient = new Client(stsConfig);
        GetCallerIdentityResponse callerIdentityResponse = stsClient.getCallerIdentity();
        // return callerIdentityResponse.getBody().toMap().toString();
        return JSON.toJSONString(callerIdentityResponse.getBody());

    }

}
