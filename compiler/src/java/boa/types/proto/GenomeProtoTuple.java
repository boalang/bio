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
 public class GenomeProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("refseq", counter++);
		members.add(new BoaString());

		names.put("taxid", counter++);
		members.add(new BoaString());

		names.put("sequence", counter++);
		members.add(new BoaProtoList(new SequenceProtoTuple()));

		names.put("assemblerRoot", counter++);
		members.add(new AssemblerRootProtoTuple());


		}

	/**
	 * Construct a GenomeProtoTuple
	 */	

	 public GenomeProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.GFeature.Genome";
	}

 }
 