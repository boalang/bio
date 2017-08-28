

var selected_node=null;


//var stats_data=null;
//Plotly.d3.csv("data/boacsv_bacteria.csv", function(data){ 
//    stats_data=data;
//});
//  
var assembler_data=null;
Plotly.d3.csv("data/assemblerdata.csv", function(data){ 
    assembler_data=data;
});

    
    
var margin = {top: 20, right: 120, bottom: 20, left: 120},
    width = 2960 - margin.right - margin.left,
    height = 2800 - margin.top - margin.bottom;

var i = 0,
    duration = 750,
    root;

var tree = d3.layout.tree()
    .size([height, width]);

var diagonal = d3.svg.diagonal()
    .projection(function(d) { return [d.y, d.x]; });


// Define the zoom function for the zoomable tree

//function zoom() {
//        console.log(" FIXME!!!")  //FIXME
//        svgGroup.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
//    }
function zoom() {
  svg.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
}

// define the zoomListener which calls the zoom function on the "zoom" event constrained within the scaleExtents
var zoomListener = d3.behavior.zoom().scaleExtent([0.1, 3]).on("zoom", zoom);


var svg = d3.select("#tree-container")
  .append("svg")
    .attr("width", 1100)
    .attr("height", 1000)
    .style('border','2px solid rgb(144, 144, 144)')
    .call(d3.behavior.zoom().scaleExtent([1, 8]).on("zoom", zoom))

//    .call(zoomListener)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

d3.json("data_9_17/tol_09_17.txt", function(error, flare) {
  if (error) throw error;

  root = flare;
  root.x0 = height / 2;
  root.y0 = 0;

  function collapse(d) {
    if (d.children) {
      d._children = d.children;
      d._children.forEach(collapse);
      d.children = null;
    }
  }

  root.children.forEach(collapse);
  update(root);
});

d3.select(self.frameElement).style("height", "800px");

function update(source) {

    
  // Compute the new tree layout.
  var nodes = tree.nodes(root).reverse(),
      links = tree.links(nodes);

  // Normalize for fixed-depth.
  nodes.forEach(function(d) { d.y = d.depth * 180; });

  // Update the nodes…
  var node = svg.selectAll("g.node")
      .data(nodes, function(d) { return d.id || (d.id = ++i); });

  // Enter any new nodes at the parent's previous position.
  var nodeEnter = node.enter().append("g")
      .attr("class", "node")
      .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
      .on("click", click);

  nodeEnter.append("circle")
      .attr("r", 1e-6)
      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

  nodeEnter.append("text")
      .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
      .attr("dy", ".35em")
      .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
      .text(function(d) { return d.name; })
      .style("fill-opacity", 1e-6);

  // Transition nodes to their new position.
  var nodeUpdate = node.transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

  nodeUpdate.select("circle")
      .attr("r", 4.5)
      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

  nodeUpdate.select("text")
      .style("fill-opacity", 1);

  // Transition exiting nodes to the parent's new position.
  var nodeExit = node.exit().transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
      .remove();

  nodeExit.select("circle")
      .attr("r", 1e-6);

  nodeExit.select("text")
      .style("fill-opacity", 1e-6);

  // Update the links…
  var link = svg.selectAll("path.link")
      .data(links, function(d) { return d.target.id; });

  // Enter any new links at the parent's previous position.
  link.enter().insert("path", "g")
      .attr("class", "link")
      .attr("d", function(d) {
        var o = {x: source.x0, y: source.y0};
        return diagonal({source: o, target: o});
      });

  // Transition links to their new position.
  link.transition()
      .duration(duration)
      .attr("d", diagonal);

  // Transition exiting nodes to the parent's new position.
  link.exit().transition()
      .duration(duration)
      .attr("d", function(d) {
        var o = {x: source.x, y: source.y};
        return diagonal({source: o, target: o});
      })
      .remove();

  // Stash the old positions for transition.
  nodes.forEach(function(d) {
    d.x0 = d.x;
    d.y0 = d.y;
  });
}

// Toggle children on click.
function click(d) {
  console.log(d);

  var text = '<table class="container table-responsive table-striped table-hover"                   style="font-size:13px">';
    
  text += '<tr><th>' +' Taxanomy ID: ' +'</th>' + '<td>'+d.taxid +'</td> </tr>'; 
  text += '<tr><th>' +' Scientific Name: ' +'</th>' + '<td>'+d.name +'</td> </tr>';
  text += '<tr><th>' +' Number of GFF files: ' +'</th>' + '<td>'+d.leaves.length +'</td> </tr>';   
  text += '<tr><th>' +' Depth: ' +'</th>' + '<td>'+d.depth+'</td> </tr>';
//  text += '<tr><th>' +' Children: ' +'</th>' + '<td>'+d._children.length+'</td> </tr>';       
  text += ' <tr class="table-info"> <th> See NCBI </th> <td><a href= https://www.ncbi.nlm.nih.gov/Taxonomy/Browser/wwwtax.cgi?id=' + d.taxid +' target="_blank" >' + ' NCBI' + '</a>'  + '</td></tr>'
  text += '</table>'  
  
  $("#node_property").html(text);
    
  if (selected_node!=null){
    d3.select(selected_node)
      .style('fill', "black");    
  }
  selected_node=this;

  d3.select(this)
    .style("fill","red");
    
  if (d.children) {
    d._children = d.children;
    d.children = null;
  } else {
    d.children = d._children;
    d._children = null;
  }
    
  //Create charts
  processFeatureData2(d);    
  
  update(d);
}



function processFeatureData2(d) {

        var exon = [], 
            gene = [], 
            mRNA = [], 
            CDS = [], 
            exon_length = [], 
            gene_length = [], 
            mRNA_length = [], 
            CDS_length = [], 
            standard_deviation = [];

        
              
            for (var i=0; i<d.leaves.length; i++) {
                    gene.push( d.leaves[i][1]);
                    exon.push(d.leaves[i][3]  );
                    mRNA.push( d.leaves[i][5] );
                    CDS.push( d.leaves[i][7] );
                    gene_length.push(d.leaves[i][2] );
                    exon_length.push(d.leaves[i][4]);
                    mRNA_length.push(d.leaves[i][6]);
                    CDS_length.push(d.leaves[i][8] );
               

            }

            makeNoPlotly( exon, gene, mRNA, CDS);
            makeSizePlotly( exon_length, gene_length, mRNA_length, CDS_length);

       
      

        //show assembler data
        
            var assemblers = [], 
                counts = [], 
                standard_deviation = [];
            
            for (var i=0; i<d.assemblers.length; i++) {
               
               var row = assembler_data[i];
               var assembler=String(d.assemblers[i]).toLowerCase();
                
                   
                if (assembler.length==0  || assembler.localeCompare("nan") ){
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
	
            var data = [{
                values: counts,
                labels: assemblers,
                type: 'pie',
//                hoverinfo: 'label+percent+name',
//                textposition : "inside"
                textposition : 'inside',
                textinfo : 'label+percent'

            }];
           var layout = {
              height: 600,
              width: 500,
   
            };


           Plotly.newPlot('assembler_programs', data, layout);

        };

var svgGroup = svg.append("g");
