package mypoc.euh;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mypoc.euh.config.AppConfig;
import mypoc.euh.config.DBConfig;
import mypoc.euh.entity.LampEntity;
import mypoc.euh.exception.BaseException;
import mypoc.euh.service.LampService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppConfig.class, DBConfig.class})
public class LampServiceImplTest {

	private @Autowired LampService lampService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testNegativeCreate() throws BaseException {
		
		/**Code validation*/
		//If lamp code is NULL
		LampEntity lamp = new LampEntity(null, 0L);
		thrown.expect(BaseException.class);
		thrown.expectMessage("Invalid code");
		lampService.create(lamp);
		//If lamp code is empty string ""
		lamp = new LampEntity("LMP00178901", 0L);
		lampService.create(lamp);
		//Code length should be less than 10 chars
		lamp = new LampEntity("LMP00178901", 0L);
		thrown.expectMessage("Code length should be less than 10 chars");
		lampService.create(lamp);

	}
	
	@Test
	public void testPositiveCreate() throws BaseException {
		LampEntity lamp = new LampEntity("LMP001", 0L);
		lampService.create(lamp);
		
	}
}
