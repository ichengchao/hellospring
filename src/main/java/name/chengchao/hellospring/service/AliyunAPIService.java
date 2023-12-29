package name.chengchao.hellospring.service;

import com.aliyun.sts20150401.Client;
import com.aliyun.sts20150401.models.GetCallerIdentityResponse;

public class AliyunAPIService {

	public static void main(String[] args) throws Exception {
		test();
	}

	public static void test() throws Exception {
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
		System.out.println(callerIdentityResponse.getBody().toMap());

	}

}
