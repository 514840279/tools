package org.tools.application.common.string;

public class IdUtils {

	public static IdInfo check(String id) {
		//
		if (IdentityDocumentUtil.validateCard(id)) {
			IdInfo id = new IdInfo();
			id.setId_出生日期(IdentityDocumentUtil.getBirthByIdCard(id));

		}
		return null;
	}
	
}
