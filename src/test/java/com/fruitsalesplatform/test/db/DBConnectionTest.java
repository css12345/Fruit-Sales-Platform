package com.fruitsalesplatform.test.db;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.entity.CommoditiesVo;
import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.test.entity.User;

public class DBConnectionTest {
	private String resource = "beans.xml";
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession = null;

	public SqlSession getSqlSession() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(resource);
		sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactoryBean");
		sqlSession = sqlSessionFactory.openSession();
		context.close();
		return sqlSession;
	}

	// @Test
	public void TestSelect() throws Exception {
		sqlSession = getSqlSession();
		User user = sqlSession.selectOne("test.findUserByName", "张三");
		System.out.println("取出的用户信息：");
		System.out.println("账号：" + user.getUsername());
		System.out.println("密码：" + user.getPassword());
		System.out.println("姓名：" + user.getName());
		System.out.println("电话：" + user.getTelephone());
	}

	@Test
	public void TestUserFind() {
		try {
			sqlSession = getSqlSession();
			Map<Object, Object> map = new HashMap<>();
			map.put("username", "jack");
			map.put("password", "1234");
			List<com.fruitsalesplatform.entity.User> list = sqlSession
					.selectList("com.fruitsalesplatform.mapper.UserMapper.find", map);
			for (com.fruitsalesplatform.entity.User user : list)
				System.out.println(user.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void TestUserGet() throws Exception {
		sqlSession = getSqlSession();
		com.fruitsalesplatform.entity.User user = sqlSession.selectOne("com.fruitsalesplatform.mapper.UserMapper.get",
				"3af57d0545766ec940d2c32a6567cc06ae5");
		System.out.println(user.getName());
	}

	// @Test
	public void TestUserInsert() throws Exception {
		sqlSession = getSqlSession();
		com.fruitsalesplatform.entity.User user = new com.fruitsalesplatform.entity.User();
		user.setUserId("72a06db8-495a-4f6b-87fc-31dcf5c93eb6");
		user.setUserName("lucy");
		user.setName("李四");
		user.setPassword("123456");
		user.setTelephone("15358947561");
		sqlSession.insert("com.fruitsalesplatform.mapper.UserMapper.insert", user);
	}

	// "90h7dv5c-9j87-24r6-9087-anune089x476" "任宇" "13222222222" "上海市虹口区" "1"
	// "2017-09-04 09:19:55"

	// @Test
	public void TestRetailerDeleteById() throws Exception {
		sqlSession = getSqlSession();
		sqlSession.delete("com.fruitsalesplatform.mapper.RetailerMapper.deleteById",
				"90h7dv5c-9j87-24r6-9087-anune089x476");
	}

	// @Test
	public void TestRetailerInsert() throws Exception {
		sqlSession = getSqlSession();
		Retailer retailer = new Retailer();
		retailer.setRetailerId("90h7dv5c-9j87-24r6-9087-anune089x476");
		retailer.setName("任宇");
		retailer.setTelephone("13222222222");
		retailer.setAddress("上海市虹口区");
		retailer.setStatus(1);
		retailer.setCreateTime("2017-09-04 09:19:55");
		sqlSession.insert("com.fruitsalesplatform.mapper.RetailerMapper.insert", retailer);
	}

	// @Test
	public void TestRetailerUpdate() throws Exception {
		sqlSession = getSqlSession();
		Retailer retailer = new Retailer();
		retailer.setRetailerId("90h7dv5c-9j87-24r6-9087-anune089x476");
		retailer.setName("任宇");
		sqlSession.insert("com.fruitsalesplatform.mapper.RetailerMapper.update", retailer);
	}

	// @Test
	public void TestRetailerDelete() throws Exception {
		sqlSession = getSqlSession();
		String[] ids = { "351ab130-07c4-4a82-b713-8f71328111bc", "45j8r40p-4fu7-87t4-8723-sdfjh789x907",
				"88e6ec6c-6d17-43a7-8782-d1eae394d802" };
		sqlSession.delete("com.fruitsalesplatform.mapper.RetailerMapper.delete", ids);
	}

	@Test
	public void TestRetailerFind() {
		try {
			sqlSession = getSqlSession();
			Map<String, Object> map = new HashMap<>();
			map.put("startTime", null);
			map.put("endTime", null);
			map.put("name", null);
			map.put("status", null);
			map.put("address", null);
			map.put("telephone", "%1%");
			map.put("startPage", null);
			map.put("pageSize", null);
			map.put("createTime", null);
			List<Retailer> list = sqlSession.selectList("com.fruitsalesplatform.mapper.RetailerMapper.find", map);
			for (Retailer retailer : list)
				System.out.println(retailer.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//assertThat(e.getMessage(), containsString(""));
		}
		fail("java.lang.NullPointerException");
	}

	// @Test
	public void TestContractGet() throws Exception {
		sqlSession = getSqlSession();
		Contract contract = sqlSession.selectOne("com.fruitsalesplatform.mapper.ContractMapper.get",
				"d14c4f85-76d6-4ef9-97ec-568004d3b8f8");
		System.out.println(contract.getContractId() + " " + contract.getBarCode() + " " + contract.getType() + " "
				+ contract.getCreateTime());
		Retailer retailer = contract.getRetailer();
		System.out.println(retailer.getRetailerId() + " " + retailer.getName() + " " + retailer.getTelephone() + " "
				+ retailer.getAddress());
		List<CommoditiesVo> commditiesList = contract.getCommoditiesList();
		for (CommoditiesVo commditiesVo : commditiesList) {
			System.out.println(commditiesVo.getFruitId() + " " + commditiesVo.getName() + " " + commditiesVo.getPrice()
					+ " " + commditiesVo.getLocality() + " " + commditiesVo.getNumber());
			List<Accessory> accessoryList = commditiesVo.getAccessoryList();
			for (Accessory accessory : accessoryList)
				System.out.println(accessory.getName() + " " + accessory.getPrice());
		}
	}

	// @Test
	public void TestFindContractList() throws Exception {
		sqlSession = getSqlSession();
		HashMap<String, Object> map = new HashMap<>();
		map.put("type", 0);
		map.put("retailerName", "刘成成");
		List<ContractVo> contractVoList = sqlSession
				.selectList("com.fruitsalesplatform.mapper.ContractMapper.findContractList", map);
		for (ContractVo contractVo : contractVoList)
			System.out.println(contractVo.getContractId() + " " + contractVo.getBarCode() + " "
					+ contractVo.getRetailerName() + " " + contractVo.getCreateTime() + " " + contractVo.getType());
		int num = sqlSession.selectOne("com.fruitsalesplatform.mapper.ContractMapper.count");
		System.out.println(num);
	}

	// "5636d4e7-36c3-4f5d-b11c-049c8c1f8945"
	// "351ab130-07c4-4a82-b713-8f71328111bc" "201709250001" "0" "2017-09-25
	// 19:13:20"

	// @Test
	public void TestContractInsert() throws Exception {
		sqlSession = getSqlSession();
		Contract contract = new Contract();
		contract.setContractId("5636d4e7-36c3-4f5d-b11c-049c8c1f8946");
		Retailer retailer = new Retailer();
		retailer.setRetailerId("351ab130-07c4-4a82-b713-8f71328111bc");
		contract.setRetailer(retailer);
		contract.setBarCode("201809110001");
		contract.setType(1);
		contract.setCreateTime("2018-09-11 22:01:20");
		sqlSession.insert("com.fruitsalesplatform.mapper.ContractMapper.insert", contract);
		sqlSession.commit();
	}

	// @Test
	public void TestMiddleTabInsert() throws Exception {
		sqlSession = getSqlSession();
		MiddleTab middleTab = new MiddleTab();
		middleTab.setContractId("5636d4e7-36c3-4f5d-b11c-049c8c1f8946");
		middleTab.setFruitId("88e6ec6c-6d17-43a7-8782-89asdjh389a");
		middleTab.setMiddleId("b13bc528-db03-423f-a73b-839d2bb3b881");
		middleTab.setNumber(10);
		sqlSession.insert("com.fruitsalesplatform.mapper.ContractMapper.insertMiddleTab", middleTab);
		sqlSession.commit();
	}

	// @Test
	public void TestContractUpdate() throws Exception {
		sqlSession = getSqlSession();
		Contract contract = new Contract();
		contract.setContractId("5636d4e7-36c3-4f5d-b11c-049c8c1f8946");
		Retailer retailer = new Retailer();
		retailer.setRetailerId("45j8r40p-4fu7-87t4-8723-sdfjh789x907");
		contract.setRetailer(retailer);
		sqlSession.update("com.fruitsalesplatform.mapper.ContractMapper.update", contract);
		sqlSession.commit();
	}
}
