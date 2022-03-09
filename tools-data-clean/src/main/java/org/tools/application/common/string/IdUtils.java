package org.tools.application.common.string;

public class IdUtils {

	public static IdInfo check(String id) {
		//
		if (IdentityDocumentUtil.validateCard(id)) {
			IdInfo idinfo = new IdInfo();
			idinfo.setBirthday(IdentityDocumentUtil.getBirthByIdCard(idinfo.getSfzh18()));

		}
		return null;
	}
	
}
