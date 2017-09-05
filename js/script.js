var taxlist=[];
var selectedtax=[];
var jsonfile;
var tax_key="";
var filtered_list=[];


d3.json("/data_9_17/nodes_stats.txt", function(data){
    jsonfile=data;

});



//var jsonData=$.getJSON('/data/nodes_stats.txt', function(data) {      
//    return(data);
//});




function makeplot() {
 	//Plotly.d3.csv("data_4_17/data1.csv", function(data){ processData(data) } );
    
    process_json_data();
};


function makeAssemblyplot() {
 	Plotly.d3.csv("data_4_17/nodeAssembler.csv", function(data){ processAssemblyData(data) } );

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

                if (row['parentTaxid']==selectedtax[j]){
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

function process_json_data() {

	var exon = [], 
        gene = [], 
        mRNA = [], 
        CDS = [],
        exon_length = [], 
        gene_length = [], 
        mRNA_length = [], 
        CDS_length = [], 
        standard_deviation = [];
        
    
    filtered_list=[];
    
    for(var j=0; j<selectedtax.length; j++){

         var returned_item = jsonfile.nodes.filter(function (item) {
         var returnData=[];
         if (item.taxid==selectedtax[j]){
            //console.log(item);
            var el={taxid: item.taxid, leaves:item.leaves};
            returnData.push(el);

            return returnData;
     }
      });// end of returned_item
        
    filtered_list.push(returned_item)    
        
        
	}// end of filter list

    for(var i=0; i<filtered_list.length; i++){
        if (filtered_list[0].length==0){
            break;
        }
        var leaves=filtered_list[i][0].leaves;
        for (var j=0; j<leaves.length;j++){
            var row=String(leaves[j]);
            var data_row=row.split(',');
            
            gene.push( data_row[0] );
            gene_length.push( data_row[1] );
            exon.push( data_row[2] );
            exon_length.push( data_row[3] );
            mRNA.push( data_row[4] );
            mRNA_length.push( data_row[5] );
            CDS.push( data_row[6] );
            CDS_length.push( data_row[7] );
            
        }
        
        //parse assemblr data
        
        var assemblers = [], 
                counts = [], 
                standard_deviation = [];
            
        var filtered_assemlers=filtered_list[i][0].assemblers;
        for (var i=0; i<filtered_assemlers.length; i++) {
               
               var assembler=String(filtered_assemlers[i]).toLowerCase();
                                   
                if (assembler.length==0  || assembler==="nan" ){
                    assembler='unknown';
                }
                if(assemblers.indexOf(assembler)==-1){
                    assemblers.push(assembler);               
                    counts[assemblers.indexOf(assembler)]=1;

                }else{
                counts[assemblers.indexOf(assembler)]++;
                }
               
            }

            makeAssemblyPlotly( assemblers, counts);
        //
       
    }
    
    makeNoPlotly( exon, gene, mRNA, CDS);
    makeSizePlotly( exon_length, gene_length, mRNA_length, CDS_length);

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
            var assembler=row['assembler'].toLowerCase();
            if (row['parentTaxid']==selectedtax[j]){

                if (assembler.length==0){
                    assembler='unknown';
                }
                if(assemblers.indexOf(assembler)==-1){
                    assemblers.push(assembler);               
                    counts[assemblers.indexOf(assembler)]=1;

                }else{
                counts[assemblers.indexOf(assembler)]++;
                }

            }

        }
    }
    makeAssemblyPlotly( assemblers, counts);

}



function makeNoPlotly( exon, gene, mRNA, CDS ){

        var Exon_No = {
          y: exon,
          boxpoints: 'outliers',
          name: 'Exon_No',    
          type: 'box'
        };

        var Gene_No = {
          y: gene,
          boxpoints: 'outliers', 
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
          boxpoints: 'outliers',   
          name: 'CDS_No',
          type: 'box'
        };

        var data = [Exon_No, Gene_No,mRNA_No, CDS_No];

        Plotly.newPlot('feature_no', data);


    };


 function makeSizePlotly( exon, gene, mRNA, CDS ){

            var Exon_length = {
              y: exon,
              boxpoints: 'outliers',
              name: 'Exon_Length',    
              type: 'box'
            };

            var Gene_length = {
              y: gene,
              boxpoints: 'outliers', 
              name: 'Gene_Length',    
              type: 'box'
            };

            var mRNA_length = {
              y: mRNA,
              boxpoints: 'outliers',
              name: 'mRNA_Length',    
              type: 'box'
            };

            var CDS_length = {
              y: CDS,
              boxpoints: 'outliers',   
              name: 'CDS_Length',
              type: 'box'
            };

            var data = [Exon_length, Gene_length,mRNA_length, CDS_length];

            Plotly.newPlot('feature_size', data);

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
//      height: 380,
//      width: 480,
      title:'assembler programs'

    };

   
   Plotly.newPlot('assemblers_div', data, layout);
    
   
    

};
            

function fillList(){

        var data=[];
        for (var i=0; i<taxlist.length; i++){
            data.push({id: taxlist[i], text: taxlist[i]});
        }

        $(".taxonomylist").select2({
          data: data,
          placeholder: "Select a taxonomy ID",
          allowClear: true,
          maximumSelectionLength: 4
    
            
        })

//        $("#yourdropdownid option:selected").text();

        $(".taxonomylist").on('select2:select', function(event){
            var value = $(event.currentTarget).find("option:selected").val() + "&#10;";
            var txtData=document.getElementById("texlist").value.trim();
            if (txtData=="" ||
                txtData.length==0){
                //document.getElementById("texlist").value="";
                document.getElementById("texlist").innerHTML=value;
            }else{
                document.getElementById("texlist").innerHTML +=value; 
                
            }
        })
          
}

  function showSum(){
//                if (document.getElementById("texlist").innerHTML==""){
//                    return;
//                }
                var lines=document.getElementById("texlist").value;
                selectedtax=lines.split("\n");
                makeplot();
            }

function loadsample(){
     document.getElementById("texlist").value="";
     document.getElementById("texlist").value +='1760' + "\n";
     document.getElementById("texlist").value +='695850' + "\n";
     document.getElementById("texlist").value +='352914' + "\n";
     document.getElementById("texlist").value +='222544' + "\n";
    

}


function initialize(){
    
    clearList();

    Plotly.d3.csv("data_9_17/taxlist.txt", function(data){
        for (var i=0; i<data.length; i++) {
            var row = data[i];

            if (!taxlist.includes(row['taxid'])){
                taxlist.push(row['taxid']);
            }
        }
        fillList();
         } );
    
}

function clearList(){
             document.getElementById("texlist").value="";


    element = document.getElementById('texlist');
    if (element != null) {
         document.getElementById("texlist").innerHTML="";
    }
    
    
}
//initialize()