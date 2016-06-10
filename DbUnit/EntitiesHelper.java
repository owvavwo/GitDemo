package com.hui.maven.util;

import org.junit.Assert;

import com.hui.maven.model.User;

public class EntitiesHelper {
	private static User baseUser = new User(1,"admin","123","管理员");
	
	public static void assertUser(User expected, User actual) {
		Assert.assertNotNull(expected);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getUsername(), actual.getUsername() );
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getNickname(), actual.getNickname());
	}
	
	public static void assertUser(User expected) {
		assertUser(expected, baseUser);
	}
}
