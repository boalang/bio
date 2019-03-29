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
 public class AnnotationProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("keyID", counter++);
		members.add(new BoaString());

		names.put("defline", counter++);
		members.add(new BoaString());

		names.put("tax_id", counter++);
		members.add(new BoaString());

		names.put("tax_name", counter++);
		members.add(new BoaString());


		}

	/**
	 * Construct a AnnotationProtoTuple
	 */	

	 public AnnotationProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.Nr2.Annotation";
	}

 }
 