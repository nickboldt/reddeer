package org.jboss.reddeer.snippet.test;

import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.snippet.requirement.UserRequirement.User;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
@User(name = "admin")
public class UserTest {
	
	@Test
	public void test() {
	}
}
