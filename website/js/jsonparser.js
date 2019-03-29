
var keyword="";

var jsonData=$.getJSON('/data/data.txt', function(data) {      
    return(data);
});


var items2 = jsonData.responseJSON.people.filter(function (item) {
     var returnData=[];
     if (item.name==keyword){
        //console.log(item);
        var el={name: item.name, website:item.website};
        returnData.push(el);
        return returnData;
     }
      });