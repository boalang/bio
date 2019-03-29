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
 public class AssemblerRootProtoTuple extends BoaProtoTuple {
	private final static List < BoaType> members = new ArrayList<BoaType>();
	private final static Map <String, Integer> names = new HashMap<String, Integer>();

	static {
		int counter = 0;

		names.put("refseq", counter++);
		members.add(new BoaString());

		names.put("assembler", counter++);
		members.add(new BoaProtoList(new AssemblerProtoTuple()));

		names.put("total_length", counter++);
		members.add(new BoaInt());

		names.put("scaffold_count", counter++);
		members.add(new BoaInt());

		names.put("scaffold_N50", counter++);
		members.add(new BoaInt());

		names.put("contig_N50", counter++);
		members.add(new BoaInt());

		names.put("contig_count", counter++);
		members.add(new BoaInt());

		names.put("total_gap_length", counter++);
		members.add(new BoaInt());

		names.put("assembly_date", counter++);
		members.add(new BoaInt());


		}

	/**
	 * Construct a AssemblerRootProtoTuple
	 */	

	 public AssemblerRootProtoTuple() {
		super(members, names);
	 }

	/** @{inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.types.GFeature.AssemblerRoot";
	}

 }
 