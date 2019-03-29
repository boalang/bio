var taxlist=[];
var selectedtax=[];
var jsonfile;
var assembler_jsonfile;

var tax_key="";
var filtered_list=[];


d3.json("data_9_17/nodes_stats.txt", function(data){
    jsonfile=data;

});

d3.json("data_9_17/nodes_assembly_stats.json", function(data){
    assembler_jsonfile=data;

});


//var jsonData=$.getJSON('/data/nodes_stats.txt', function(data) {      
//    return(data);
//});



function makeplot() {
 	//Plotly.d3.csv("data_4_17/data1.csv", function(data){ processData(data) } );
    
    process_json_data();
    process_assembly_json_data();
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
        console.log(leaves.length);
        
        //Node proprties: like number of GFFs
        var text = ' <br>'+'<table class="container table-responsive table-striped table-hover"                   style="font-size:20px">';
    
//        text += '<tr><th>' +' Taxanomy ID: ' +'</th>' + '<td>'+d.taxid +'</td> </tr>'; 
//        text += '<tr><th>' +' Scientific Name: ' +'</th>' + '<td>'+d.name +'</td> </tr>';
        text += '<tr><th>' +' Number of Species: ' +'</th>' + '<td>'+leaves.length +'</td> </tr>';   
//        text += ' <tr class="table-info"> <th> See NCBI </th> <td><a href= https://www.ncbi.nlm.nih.gov/Taxonomy/Browser/wwwtax.cgi?id=' + d.taxid +' target="_blank" >' + ' NCBI' + '</a>'  + '</td></tr>'
        text += '</table>'  

        $("#node_property").html(text);

        //
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
        
        //parse assembler data
        
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
               
            } //End of parsing assembler data
       
    }
    
    makeNoPlotly( exon, gene, mRNA, CDS);
    makeSizePlotly( exon_length, gene_length, mRNA_length, CDS_length);
    makeAssemblyPlotly( assemblers, counts);


}//End of process_json_data()


function process_assembly_json_data() {

	var total_length = [], 
        total_gap_length = [], 
        scaffold_count = [], 
        scaffold_N50 = [],
        contig_count = [], 
        contig_N50 = [], 
        standard_deviation = [];
        
    filtered_list=[];
    
    for(var j=0; j<selectedtax.length; j++){

         var returned_item = assembler_jsonfile.nodes.filter(function (item) {
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
            
            total_length.push( data_row[2] );
            total_gap_length.push( data_row[3] );
            scaffold_count.push( data_row[4] );
            scaffold_N50.push( data_row[5] );
            contig_count.push( data_row[6] );
            contig_N50.push( data_row[7] );
            
            
        }
        
        
       
    }
    
    makeAssemblyStatsPlotly( total_length, total_gap_length, scaffold_count, scaffold_N50, contig_count, contig_N50 );


}//End of process_json_data()



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
          type: 'box',
            boxmean: 'sd'
        };

        var Gene_No = {
          y: gene,
          boxpoints: 'outliers', 
          name: 'Gene_No',    
          type: 'box',
            boxmean: 'sd'
        };

        var mRNA_No = {
          y: mRNA,
          boxpoints: 'outliers',
          name: 'mRNA_No',    
          type: 'box',
            boxmean: 'sd'
        };

        var CDS_No = {
          y: CDS,
          boxpoints: 'outliers',   
          name: 'CDS_No',
          type: 'box',
            boxmean: 'sd'
        };

        var layout = {
              title: ' Genome Feature Number statistics'
        };

        var data = [Exon_No, Gene_No,mRNA_No, CDS_No];

        Plotly.newPlot('feature_no', data,layout);


    };


 function makeSizePlotly( exon, gene, mRNA, CDS ){

            var Exon_length = {
              y: exon,
              boxpoints: 'outliers',
              name: 'Exon_Length',    
              type: 'box',
              boxmean: 'sd'
            };

            var Gene_length = {
              y: gene,
              boxpoints: 'outliers', 
              name: 'Gene_Length',    
              type: 'box',
              boxmean: 'sd'
            };

            var mRNA_length = {
              y: mRNA,
              boxpoints: 'outliers',
              name: 'mRNA_Length',    
              type: 'box',
              boxmean: 'sd'

            };

            var CDS_length = {
              y: CDS,
              boxpoints: 'outliers',   
              name: 'CDS_Length',
              type: 'box',
              boxmean: 'sd'
            };

            var layout = {
              title: 'Genome Feature Size statistics'
            };
     
            var data = [Exon_length, Gene_length,mRNA_length, CDS_length];

            Plotly.newPlot('feature_size', data,layout);

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
      title:'Assembler Programs'

    };

   Plotly.newPlot('assemblers_div', data, layout);
    
};
 

 function makeAssemblyStatsPlotly( total_length, total_gap_length, scaffold_count, scaffold_N50, contig_count, contig_N50 ){

            var total_length_box = {
              y: total_length,
              boxpoints: 'outliers',
              name: 'total_length',    
              type: 'box',
              boxmean: 'sd'    
            };

            var total_gap_length_box = {
              y: total_gap_length,
              boxpoints: 'outliers', 
              name: 'total_gap_length',    
              type: 'box',
              boxmean: 'sd'
            };

            var scaffold_count_box = {
              y: scaffold_count,
              boxpoints: 'outliers',
              name: 'scaffold_count',    
              type: 'box',
              boxmean: 'sd'
            };

            var scaffold_N50_box = {
              y: scaffold_N50,
              boxpoints: 'outliers',   
              name: 'scaffold_N50',
              type: 'box',
                boxmean: 'sd'
            };

            var contig_count_box = {
              y: contig_count,
              boxpoints: 'outliers',   
              name: 'contig_count',
              type: 'box',
                boxmean: 'sd'
            };
     
            var contig_N50_box = {
              y: contig_N50,
              boxpoints: 'outliers',   
              name: 'contig_N50',
              type: 'box',
                boxmean: 'sd'
            };
     
     
            var layout = {
              title: 'Asssembly statistics'
            };
     
            var data = [total_length_box, total_gap_length_box, scaffold_count_box, scaffold_N50_box, contig_count_box, contig_N50_box];

            // remove total length and total gap-length
            //var data = [scaffold_count_box, scaffold_N50_box, contig_count_box, contig_N50_box];
     
            Plotly.newPlot('assemblyStats_div', data,layout);

        };



function fillList(){

        var data=[];
//        for (var i=0; i<taxlist.length; i++){
//            data.push({id: taxlist[i], text: taxlist[i]});
//        }

        $(".taxonomylist").select2({
          data: taxlist,
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
        //FIXME: data.length, Remove the species just internal nodes
        // upfate taxlist file
        for (var i=0; i<2000; i++) {
            var row = data[i];

            if (!taxlist.includes(row['taxid'])){
                taxlist.push({id:row['taxid'], text:row['name']});
            }
        }
        fillList();
         } );
    
}

function clearList(){
    //FIXME
    //document.getElementById("texlist").value="";


    element = document.getElementById('texlist');
    if (element != null) {
         document.getElementById("texlist").innerHTML="";
    }
    
    
}
initialize()