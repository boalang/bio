//
//var datacsv;
//d3.csv("/data/data1.csv", function(csv) {
//  csv.forEach(function(d) {
//    d.taxid = +d.taxid;
//    d["parentTaxid"] = +d["parentTaxid"];
//  });
//
//  var newdata=csv.filter(function(d) { 
//      return d.close < 400 
//  });  
//
//  csv = csv.filter(function(row) {
//        if(row['parentTaxid'] == '715989')
//            return row['exon'];
//    })    
//    
//  console.log(csv);
//  datacsv=csv;
//});


function makeplot() {
 	Plotly.d3.csv("data/data1.csv", function(data){ processData(data) } );

};

	
function processData(allRows) {

	console.log(allRows);
	var exon = [], 
        gene = [], 
        mRNA = [], 
        CDS = [], 
        standard_deviation = [];

	for (var i=0; i<allRows.length; i++) {
		var row = allRows[i];
        if (row['parentTaxid']=='715989'){
            exon.push( row['exon'] );
		    gene.push( row['gene'] );
            mRNA.push( row['mRNA'] );
            CDS.push( row['CDS'] );
        }
		
	}
	console.log( 'X',exon, 'Y',gene );

    makePlotly( exon, gene, mRNA, CDS);

}


function makePlotly( exon, gene, mRNA, CDS ){
	
    var Exon_No = {
      y: exon,
      boxpoints: 'all',
      name: 'Exon_No',    
      type: 'box'
    };

    var Gene_No = {
      y: gene,
      boxpoints: 'all', 
      name: 'Gene_No',    
      type: 'box'
    };
    
    var mRNA_No = {
      y: mRNA,
      boxpoints: 'all',
      name: 'mRNA_No',    
      type: 'box'
    };
    
    var CDS_No = {
      y: CDS,
      boxpoints: 'all',   
      name: 'CDS_No',
      type: 'box'
    };

    var data = [Exon_No, Gene_No,mRNA_No, CDS_No];

    Plotly.newPlot('myDiv', data);


};

makeplot();




