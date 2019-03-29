package boa.types.proto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boa.types.*;

/**
 * A {@link BoaProtoTuple}.
 * 
 * @author nmtiwari
 */
 public class AssemblerProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("name", counter++);
		members.add(new BoaString());

		names.put("desc", counter++);
		members.add(new BoaString());


		}

	/**
	 * Construct a AssemblerProtoTuple
	 */	

	 public AssemblerProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.GFeature.Assembler";
	}

 }
 