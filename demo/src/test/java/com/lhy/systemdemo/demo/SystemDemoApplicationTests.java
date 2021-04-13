package com.lhy.systemdemo.demo;

import com.lhy.systemdemo.SystemDemoApplication;
import com.lhy.systemdemo.dao.test.TestMapper;
import com.lhy.systemdemo.pojo.Book;
import com.lhy.systemdemo.service.article.ArticleService;
import com.lhy.systemdemo.utils.BeanUtil;
import com.lhy.systemdemo.utils.UtilTest;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest(classes = SystemDemoApplication.class)
class SystemDemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
	private TestMapper testMapper;


	@Test
	void contextLoads() throws IOException {
		//创建一个原始的二维数组11*11
		//0表示没有棋子，1表示黑棋，2表示白棋
		int[][] chessArr1 = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		System.out.println("原始二维数组：");
		//遍历二维数组
		for (int[] row : chessArr1) {
			for (int data : row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		//统计原始二维数组不为0的的个数
		int count = 0;
		for (int[] row : chessArr1) {
			for (int data : row) {
				if(data != 0) {
					count++;
				}
			}
		}
		System.out.println("原始二维数组不为0的的个数为："+count);

		//定义稀疏数组
		int[][] sparseArr = new int[count+1][3];

		//稀疏数组第一行的值
		sparseArr[0][0] = chessArr1.length;
		sparseArr[0][1] = chessArr1[0].length;
		sparseArr[0][2] = count;


		//为稀疏数组其他行赋值
		int count2 = 0;//记录是第几个非零的数据
		for (int i = 1;i<chessArr1.length;i++) {
			for (int j = 0;j<chessArr1[0].length;j++) {
				if (chessArr1[i][j]!=0) {
					count2++;
					sparseArr[count2][0] = i;
					sparseArr[count2][1] = j;
					sparseArr[count2][2] = chessArr1[i][j];
				}
			}
		}

		System.out.println("");
		System.out.println("稀疏数组为：");
		//遍历稀疏数组
		for (int i =0;i<sparseArr.length;i++) {
			System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
		}

		System.out.println("开始写入文件<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		BufferedWriter out = null;
		out = new BufferedWriter(new FileWriter("D:\\Study\\map.data",true));
		for (int i=0;i<sparseArr.length;i++){
			for (int j=0;j<sparseArr[0].length;j++){
				out.write(sparseArr[i][j] + "\t");
			}
			out.newLine();
		}
		out.flush();
		System.out.println("保存文件成功");

		System.out.println("开始读取文件还原稀疏数组");
		int[][] sparseArr2 = new int[count + 1][3];
		BufferedReader bf = null;
		bf = new BufferedReader(new FileReader("D:\\Study\\map.data"));
		bf.close();
		String lineData = null;
		int rowNum = 0;
		while ((lineData = bf.readLine()) != null){
			String[] temp = lineData.split("\t");
			for (int i=0; i < temp.length;i++) {
				sparseArr2[rowNum][i] = Integer.parseInt(temp[i]);
			}
			rowNum++;
		}
		bf.close();


		System.out.println("从磁盘读取的稀疏数组为：");
		for (int i = 0;i<sparseArr2.length;i++) {
			System.out.printf("%d\t%d\t%d\t\n",sparseArr2[i][0],sparseArr2[i][1],sparseArr2[i][2]);
		}

	}

	@Test
	public void test() throws ParseException, IOException {
	/*	Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateBegin = formatter.format(now);
		Date beginWithSecond = formatter.parse(dateBegin);
		System.out.println(now.getTime());
		System.out.println(beginWithSecond.getTime());*/
		File file = new File("D:\\Person\\Record\\idea.txt");
		FileInputStream fi = new FileInputStream(file);
		int length = 1024;
		byte[] bytes = new byte[1024];
		int n = 0;
		StringBuffer buffer = new StringBuffer();
		while (n != -1){
			n = fi.read(bytes);
			if (n > 0){
				buffer.append(new String(bytes,0,n));
			}
		}
		System.out.println(buffer.toString());

	}

	@Test
	void testBeanUtil(){
		/*com.lhy.systemdemo.pojo.test.Test test = new com.lhy.systemdemo.pojo.test.Test();
		test.setCreator("test111");
		test.setModifier("super");
		test.setName("angel");
		test.setTableName("test");
		testMapper.testInsert(test);*/
        System.out.println(new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()));
	}

}
