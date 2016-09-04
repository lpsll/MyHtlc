/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 * 
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package com.autodesk.easyhome.shejijia.alipay;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

	//合作身份者id，以2088开头的16位纯数字
	public static final String DEFAULT_PARTNER = "";

	//收款支付宝账号
	public static final String DEFAULT_SELLER = "";

	//商户私钥，自助生成
	public static final String PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM4zArAW0HR84f0flp+Zyt6ycyIzxXSvSJ2uUpnRXTiW/miXM9HVtbTy/gXc97hRM+Lk6uGgR8MdKDpFMoaqTd8sK44l3m7veV3rso/ApoTZgxIxZOPE4EOzSsUoVPHwBsbTwrDus/rPuQq3dBLsFnnaXZ0mgXxmP7/mzhQLnarVAgMBAAECgYBv4FFnBRnY+iELW2Y39hOSPLQnLHvH0YrOstRyTJwNpi8mxFMDWLacFPMqbzegs745LwcZoAMPo/Q9mWnOkvxoTdYnA3AbHiyVu6TYS3DoXZEpz3APoCgvYNis5DdRR1PhVXb83dVEfrRGqh1o1bZ9klmqCdTy4hWT36+9UStywQJBAPhdXoytVPNPDG/auOrMA5JGeDYyEZ/PgaKA/dQhMUULoTRbZmm1vZTqxWPUkeVG43iaRCSG7cVg9q0CgtzVu/0CQQDUicyFkoG+BXUeoljp+YWYNKwP5Az2koopRCD/4UsJqRxtKbuh2QefVYZ3B5odsh4ok2G8tl9oF3X52hpXVmW5AkEAkgkcmi0lATeewXpjNrQk+XJ0JrHECSrTN8EO/xdRSB7xd76ydj/FrHVrASsxahYHlJdor+2ii2dbRBlw5vbJ5QJAK3QM65Y7jnUhL+UzVorcZHUIZKtUdykYtD0onggaxlvb4vmwUfPEWjArMLTOLpoXDmarieCjeu2pAi80SXzxiQJBALm2+9pJVvIQXNbzpgd4cGy6BAdm7barRs1Lwl9ZXtPfkXVJTqunPLDMmmtQWOoCbK2K1AYLUuqKeKdmrFvHpQw=";
	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

}
