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


function makeAssemblyplot() {
 	Plotly.d3.csv("data/nodeAssembler.csv", function(data){ processAssemblyData(data) } );

};


function processData(allRows) {

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

    makePlotly( exon, gene, mRNA, CDS);

}

function processAssemblyData(allRows) {

	console.log(allRows);
	var assemblers = [], 
        counts = [], 
        standard_deviation = [];

	for (var i=0; i<allRows.length; i++) {
		var row = allRows[i];
        console.log(row)
        if (row['parentTaxid']=='715989'){
            
            if (row['assembler'].length==0){
                row['assembler']='N/A';
            }
            if(assemblers.indexOf(row['assembler'])==-1){
                assemblers.push(row['assembler']);               
                counts[assemblers.indexOf(row['assembler'])]=1;

            }else{
            counts[assemblers.indexOf(row['assembler'])]++;
            }
		    
        }
		
	}

    makeAssemblyPlotly( assemblers, counts);

}

function makePlotly( exon, gene, mRNA, CDS ){
	
    var Exon_No = {
      y: exon,
      boxpoints: 'outliers',
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
      boxpoints: 'outliers',
      name: 'mRNA_No',    
      type: 'box'
    };
    
    var CDS_No = {
      y: CDS,
      boxpoints: 'all',   
      name: 'CDS_No',
      type: 'box'
    };
    
    var layout = { 
        hovermode:'closest',
        title:'Hover on Points'
     };


    var data = [Exon_No, Gene_No,mRNA_No, CDS_No];

    Plotly.newPlot('myDiv', data, layout);

    
    var myPlot = document.getElementById('myDiv'),
    hoverInfo = document.getElementById('hoverinfo');
    
   myPlot.on('plotly_hover', function(data){
    var infotext = data.points.map(function(d){
      console.log("hover")    
      return (d.data.name+': x= '+d.x+', y= '+d.y.toPrecision(3));
    });
  
    hoverInfo.innerHTML = infotext.join('<br/>');
    })
     .on('plotly_unhover', function(data){
        hoverInfo.innerHTML = '';
    });

    

};

function makeAssemblyPlotly(assemblers, counts){
	
   console.log(assemblers)
   console.log(counts);
    
   
    var data = [{
        values: counts,
        labels: assemblers,
        type: 'pie',
    
        
    }];
   var layout = {
      height: 380,
      width: 480
    };

   
   Plotly.newPlot('myDiv3', data, layout);
    
   
    
    
    

};
            
makeplot();
makeAssemblyplot()

var trace1 = {
  x: ["1", "2", "3", "4"],
  y: [10, 11, 12, 13],
  text:['e1', 'e2', 'e3','e4'],    
    
  mode: 'markers',
  marker: {
    color: ['rgb(93, 164, 214)', 'rgb(255, 144, 14)',  'rgb(44, 160, 101)', 'rgb(255, 65, 54)'],
    opacity: [1, 0.8, 0.6, 0.4],
    size: [40, 60, 80, 90]
  }
};

var data = [trace1];

var layout = {
  title: 'Marker Size and Color',
  showlegend: false,
  height: 400,
  width: 480
};

Plotly.newPlot('myDiv2', data, layout);





