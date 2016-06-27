package com.fangcheng.function;

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class shops_udtf extends GenericUDTF{
	

	@Override
	public void close() throws HiveException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public StructObjectInspector initialize(ObjectInspector[] args) throws UDFArgumentException {
		//验证输入参数的个数和类型是否正确
        if (args.length != 1) {
            throw new UDFArgumentLengthException("ExplodeMap takes only one argument");
        }
        if (args[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentException("ExplodeMap takes string as a parameter");
        }
        //定义两个返回参数col1 col2
        ArrayList<String> fieldNames = new ArrayList<String>();
        fieldNames.add("col1");
        //fieldNames.add("col2");
        //定义两个返回参数的类型为java的string类型
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        //fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs);
    }
	
	@Override
	public void process(Object[] arg0) throws HiveException {
		// TODO Auto-generated method stub
		if(arg0.length == 0)
			return;
		String[] ret = new String[1];
		String input = arg0[0].toString();
		String[] test = input.split(",");
		for(int i=0; i<test.length; i++) {
			try {
				ret[0] = test[i];
				forward(ret);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	
	public static void main(String[] args) {
		Object[] obj = new Object[1];
		obj[0]="1111,2222";
		try {
			new shops_udtf().process(obj);
		} catch (HiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
