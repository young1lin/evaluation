package me.young1lin.evaluation.api;

import me.young1lin.evaluation.api.domain.sysuser.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CallEvaluationControllerTest {

	@Resource
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Test
	public void startEvaluate() throws Exception {
		//	String expect = "";
		mockMvc.perform(MockMvcRequestBuilders
				.get("/")
				.param("d", "77"))
				//.andExpect(MockMvcResultMatchers.content()
				//	.json())
				.andDo(MockMvcResultHandlers.print());
		//userService.userIsExist(2);
	}

	@Test
	public void end() {
	}

}