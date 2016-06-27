package com.fangcheng.setjob;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class JobManager {

	private static final Logger logger = Logger.getLogger(JobManager.class.getName());//写控制台
	private Scheduler scheduler;
	
	public JobManager() throws SAXException, ParserConfigurationException,
			IOException, IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, SchedulerException {
		scheduler = StdSchedulerFactory.getDefaultScheduler();
	}

	public void start() throws Exception{
		scheduler.start();
		//System.out.println(JobManager.class.getResource("/").getPath()+"./config/service.xml");
		/**
		 * 建立一个解析器工厂
		 */
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		/**
		 * 获得具体的DOM解析器
		 */
		DocumentBuilder db = factory.newDocumentBuilder();
		/**
		 * 获得XML文档的树模型
		 */
		Document dom = db.parse(new File(JobManager.class.getResource("/").getPath()+"./config/service.xml"));
//		Document dom = UtilXml.readXmlDocument(JobManager.class.getClassLoader().getResourceAsStream("E:\\workspace_2015\\fc-hive-report\\bin\\hive_conf\\service.xml"));
//		Gson gson = new Gson();
//		System.out.println("aaaaaa:"+gson.toJson(JobManager.class.getClassLoader()));
		int jobNum = 1;
		for (Element jobEl : UtilXml.childElementList(dom.getDocumentElement(),"job")) {
			try {
				String name = jobEl.getAttribute("name");
				String group = jobEl.getAttribute("group");
				String className = jobEl.getAttribute("class");
				String schedule = jobEl.getAttribute("schedule");//规定个值，为此值的个性化执行
				//System.out.println(name);
				if("perMinute".equals(schedule))
					schedule = GetDayTime(jobNum++);  //获得时间schedul:0 25 9 5 * ?->秒 分 时 日 月 年
				else if("individuation".equals(schedule))
					schedule ="0/2 * * * * ?";
				//logger.info("打印时间"+ schedule);
				NamedNodeMap atts = jobEl.getAttributes();
				
				JobDetail jobDetail = new JobDetail(name,group, Class.forName(className));
				for(int i=0; i<atts.getLength(); i++){
					Node node = atts.item(i);
					jobDetail.getJobDataMap().put(node.getNodeName(), node.getNodeValue());
				}
				Trigger trigger = new CronTrigger(name +"_trigger", group, name, group, schedule);
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (ParseException e) {
				StringBuffer error=new StringBuffer();
			   	StackTraceElement [] messages=e.getStackTrace();
			   	int length=messages.length;
			   	for(int i=0;i<length;i++){
			   		error.append(messages[i]);
			   	}
			   	logger.info("异常信息："+error);
			}
		}
	}

	public void stop() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
	}

	public String GetDayTime(int perMin) {
		Calendar c = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append("0 ");
		if(c.get(Calendar.MINUTE)+ perMin == 60)
			sb.append("0");
		else
			sb.append(c.get(Calendar.MINUTE)+ perMin);
		sb.append(" ");
		if(c.get(Calendar.MINUTE)+ perMin == 60)
			sb.append(c.get(Calendar.HOUR_OF_DAY)+ 1);
		else
			sb.append(c.get(Calendar.HOUR_OF_DAY));
		sb.append(" ");
		sb.append(c.get(Calendar.DAY_OF_MONTH));
		sb.append(" * ?");
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		JobManager jm = new JobManager();
		jm.start();
		int test = 0;
		while(test < 3){
			logger.info("打印测试");
			test++;
			Thread.sleep(1000l);
		}
		Thread.sleep(9999999999999l);
	}
	
}
