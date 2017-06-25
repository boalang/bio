

var taxlist=[];
var selectedtax=[];

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

    for(var j=0; j<selectedtax.length; j++){

        for (var i=0; i<allRows.length; i++) {
            var row = allRows[i];

                if (row['parentTaxid']==taxlist[j]){
                    exon.push( row['exon'] );
                    gene.push( row['gene'] );
                    mRNA.push( row['mRNA'] );
                    CDS.push( row['CDS'] );
                }
            }
	}

    makePlotly( exon, gene, mRNA, CDS);

    console.log(taxlist);
}

function initialize(){
  
    Plotly.d3.csv("data/data1.csv", function(data){
        for (var i=0; i<data.length; i++) {
            var row = data[i];

            if (!taxlist.includes(data['parentTaxid'])){
                taxlist.push(row['parentTaxid']);
            }
        }
        fillList();
         } );

    

}
function processAssemblyData(allRows) {

//	console.log(allRows);
	var assemblers = [], 
        counts = [], 
        standard_deviation = [];

    for(var j=0; j<selectedtax.length; j++){

        for (var i=0; i<allRows.length; i++) {
            var row = allRows[i];
    //        console.log(row)
            if (row['parentTaxid']==taxlist[j]){

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
//      console.log("hover")    
      return (d.data.name+': x= '+d.x+', y= '+d.y.toPrecision(3));
    });
  
    hoverInfo.innerHTML = infotext.join('<br/>');
    })
     .on('plotly_unhover', function(data){
        hoverInfo.innerHTML = '';
    });

    

};

function makeAssemblyPlotly(assemblers, counts){
	
//   console.log(assemblers)
//   console.log(counts);
    
   
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


function fillList(){

            console.log("run!");
        var data=[];
        for (var i=0; i<taxlist.length; i++){
            data.push({id: taxlist[i], text: taxlist[i]});
        }

        $(".js-example-data-array").select2({
          data: data
        })

        $(".js-example-data-array-selected").select2({
          data: data
        })

        $(".js-example-data-array-selected").on('select2:select', function(event){
            var value = $(event.currentTarget).find("option:selected").val() + "&#10;";
            console.log(value);
            if (document.getElementById("texlist").value==null){
                document.getElementById("texlist").innerHTML=value;
            }else{
                document.getElementById("texlist").innerHTML +=value 
            }
        })
          
}

  function showSum(){
                var lines=document.getElementById("texlist").value;
                selectedtax=lines.split("\n");
                makeplot();
                makeAssemblyplot();
                for (var i=0; i<lines.length; i++){
                    console.log(lines[i]);
                    
//                    if (lines[i] !=""){
//                        makeplot(lines[i]);
//                        makeAssemblyplot(lines[i])
//                    }
                }
            }

initialize()