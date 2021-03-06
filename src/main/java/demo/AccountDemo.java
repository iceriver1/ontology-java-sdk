package demo;

import com.github.ontio.OntSdk;
import com.github.ontio.common.Helper;
import com.github.ontio.crypto.Digest;
import com.github.ontio.crypto.ECC;
import com.github.ontio.sdk.info.AccountInfo;
import com.github.ontio.sdk.wallet.Account;
import com.github.ontio.sdk.wallet.Identity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @date 2018/3/28
 */
public class AccountDemo {
    public static void main(String[] args) {

        try {
            OntSdk ontSdk = getOntSdk();


            byte[] salt0 = java.util.Base64.getDecoder().decode("+AX/Aa8VXp0h74PZySZ9RA==");
            String key0 = "+TDw5opWl5HfGEWUpxblVa5BqVKF2962DoCwi1GYidwWMKvOj7mqaUVx3k/utGLx";
            System.out.println(Helper.toHexString(salt0)+" "+salt0.length);
            System.out.println(Helper.toHexString(java.util.Base64.getDecoder().decode(key0)));
            String prikey0 = com.github.ontio.account.Account.getGcmDecodedPrivateKey(key0,"1","APrfMuKrAQB5sSb5GF8tx96ickZQJjCvwG", salt0,16384,ontSdk.defaultSignScheme);
            com.github.ontio.account.Account acct11 = new com.github.ontio.account.Account(Helper.hexToBytes(prikey0), ontSdk.defaultSignScheme);
            System.out.println(acct11.getAddressU160().toBase58());
           // System.exit(0);
            if (false){
                AccountInfo info0 = ontSdk.getWalletMgr().createAccountInfo("passwordtest");
                AccountInfo info = ontSdk.getWalletMgr().createAccountInfoFromPriKey("passwordtest","e467a2a9c9f56b012c71cf2270df42843a9d7ff181934068b4a62bcdd570e8be");
                System.out.println(info.addressBase58);
                Account accountInfo = ontSdk.getWalletMgr().importAccount("3JZLD/X45qSFjmRRvRVhcEjKgCJQDPWOsjx2dcTEj58=", "passwordtest",info.addressBase58,new byte[]{});

                com.github.ontio.account.Account acct0 = ontSdk.getWalletMgr().getAccount(info.addressBase58, "passwordtest",new byte[]{});
            }
            System.out.println();
            if(true){

                byte[] salt = salt0;
//                salt = ECC.generateKey(16);
                com.github.ontio.account.Account acct = new com.github.ontio.account.Account(Helper.hexToBytes("a1a38ccff49fa6476e737d66ef9f18c7507b50eb4804ed8e077744a4a2a74bb6"),ontSdk.defaultSignScheme);
                String key = acct.exportGcmEncryptedPrikey("1",salt,16384);
                System.out.println(key);
                System.out.println(acct.getAddressU160().toBase58());
                String prikey = com.github.ontio.account.Account.getGcmDecodedPrivateKey(key, "1",acct.getAddressU160().toBase58(),salt,16384,ontSdk.defaultSignScheme);
                System.out.println(prikey);
            }

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OntSdk getOntSdk() throws Exception {

        String ip = "http://127.0.0.1";
//        String ip = "http://54.222.182.88;
//        String ip = "http://101.132.193.149";
        String restUrl = ip + ":" + "20384";
        String rpcUrl = ip + ":" + "20386";
        String wsUrl = ip + ":" + "20385";

        OntSdk wm = OntSdk.getInstance();
        wm.setRpc(rpcUrl);
        wm.setRestful(restUrl);
        wm.setDefaultConnect(wm.getRestful());

        wm.openWalletFile("AccountDemo.json");

        return wm;
    }
}
