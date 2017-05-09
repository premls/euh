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
import mypoc.euh.exception.BaseException;
import mypoc.euh.io.CreateLampDTO;
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
		CreateLampDTO lamp = new CreateLampDTO(null, "OFF", 0L);
		thrown.expect(BaseException.class);
		thrown.expectMessage("Invalid code");
		lampService.create(lamp);
		//If lamp code is empty string ""
		lamp = new CreateLampDTO("LMP00178901", "OFF", 0L);
		lampService.create(lamp);
		//Code length should be less than 10 chars
		lamp = new CreateLampDTO("LMP00178901", "OFF", 0L);
		thrown.expectMessage("Code length should be less than 10 chars");
		lampService.create(lamp);
		

		/**State validation*/
		//If state is NULL
		lamp = new CreateLampDTO("LMP001", null, 0L);
		thrown.expectMessage("Invalid state");
		lampService.create(lamp);
		//If state is empty
		lamp = new CreateLampDTO("LMP001", "", 0L);
		lampService.create(lamp);
		
		/**Gateway or lamp linking*/
		//If Gateway and Lamp linking both are 
		lamp = new CreateLampDTO("LMP001", "OFF",0L);
		thrown.expectMessage("Either it should be linked to a lamp or gateway");
		lampService.create(lamp);
	}
	
	@Test
	public void testPositiveCreate() throws BaseException {
		CreateLampDTO lamp = new CreateLampDTO("LMP001", "OFF", 0L);
		lampService.create(lamp);
		
	}
}
