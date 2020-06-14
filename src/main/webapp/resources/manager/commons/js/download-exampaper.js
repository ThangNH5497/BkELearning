$(document).ready(function() {

	obj=new Download();
	examPaperId=obj.getParam('id');
	examPaper=obj.getExamPaperById(examPaperId);
	$('[field="title"]').text(examPaper.name);
	$('[field="time"]').text(examPaper.time+' Phút');
	obj.initData('data-container','row-data-sample',examPaper.examPaperQuestions)
	window.print();
//	$('#back-link').attr('href',rootLocation+'teacher/ql-lop-hoc/ql-bai-thi/danh-sach-cham-diem?examId='+'&courseId=1');
});
var examPaperId;
var examPaper;
var index=['A','B','C','D','E','F','G','H','I','J','K','L','M'];
function handleEvent()
{
	/*var url='http://127.0.0.1:8080/BkELearning/manager/downloadexampaper?id=12';
	var content=$('#main-content').html();
	obj.ajaxCall('POST',false,'api/exams/itext',content,null);*/
	//Create PDf from HTML...
	

}
/*function generatePdf(
        elementId, 
        fileName, 
        timeout = 2000
) {
  const element = $(''+elementId)[0],
      options = {
          imageTimeout: timeout,
          background: "white",
          allowTaint : true,
          useCORS: false,
       
     };

  html2canvas(element, options).then((canvas) => {
      let imgData = canvas.toDataURL('image/png');

      let imgWidth = 210,
          pageHeight = 10000,
          imgHeight = canvas.height * imgWidth / canvas.width,
          heightLeft = imgHeight,
          doc = new jsPDF('p', 'mm'),
          position = 0;

      doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
      heightLeft -= pageHeight;

      while (heightLeft >= 0) {
          position = heightLeft - imgHeight;
          doc.addPage();
          doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
          heightLeft -= pageHeight;
      }
      doc.save( fileName+'.pdf');
  });
}

function CreatePDFfromHTML() {
	
	//generatePdf('#main-content','test');
	
	  var my_view = $('#main-content');
	  var useHeight = $('#main-content')[0].offsetHeight;
	  
	 $('#main-content').css('height',useHeight+' !important');
	 html2canvas(my_view[0], {
		
		    useCORS: true,
		    allowTaint: true,
      
    }).then((canvas) => {
		 var myImage = canvas.toDataURL("image/png");
		 saveAs(myImage, 'file-name.png');
	 });
	
}
function saveAs(uri, filename) {

    var link = document.createElement('a');

    if (typeof link.download === 'string') {

        link.href = uri;
        link.download = filename;

        //Firefox requires the link to be in the body
        document.body.appendChild(link);

        //simulate click
        link.click();

        //remove the link when done
        document.body.removeChild(link);

    } else {

        window.open(uri);

    }
}
*/
class Download extends Base {
	
    constructor() {
    	super();
    }
    initData(containerId,rowDataId,data)
    {
    	var type=obj.getParam('type');
    	var border=obj.getParam('border');
    	$('#'+rowDataId).removeClass('hidden');
		var html=$('#'+rowDataId).html();
		var answerSheetHtml=$('#answer-sheet-item-sample').html();
		$('#answer-sheet-item-sample').remove();
    	$('#'+containerId).empty();
    	// hien thi du lieu
    	if(data.length>0)
    	{
    		for(var i=0;i<data.length;i++)
        	{
    			//init answer sheet
    			$('#answer-sheet').append(answerSheetHtml);
    			$($('#answer-sheet .index')[i]).text(parseInt(i)+1);
    			//init data
    			var innerHtml='';
    			if(type=="basic" && border=='false')
    			{
    				innerHtml='<div class="col-12 border-0 m-0 pr-4" dataId="'+data[i].id+'">'+html+'</div>';
    			}
    			else if(type=="basic" && border=='true')
    			{
    				innerHtml='<div class="col-12 border m-0 pr-4" dataId="'+data[i].id+'">'+html+'</div>';
    			}
    			else if(type=="double" && border=='false')
    			{
    				innerHtml='<div class="col-6 border-0 m-0 pr-4" dataId="'+data[i].id+'">'+html+'</div>';
    			}
    			else if(type=="double" && border=='true')
    			{
    				innerHtml='<div class="col-6 border m-0 pr-4" dataId="'+data[i].id+'">'+html+'</div>';
    			}
    			
    			
        		$('#'+containerId).append(innerHtml);
        		var field=$('#'+containerId+' [dataId='+data[i].id+'] [field="q-content"]');
        		var content=String(data[i].question.content);
				//remove p tag
				content=content.substring(3);
				//add patag with index question
				content='<p><b>Câu&nbsp'+(parseInt(i)+1)+'.</b>&nbsp'+content;
			
				$(field).html(content);
				field=$('#'+containerId+' [dataId='+data[i].id+'] [field="q-answers"]');
				if(data[i].question.type=='FILL_WORD')
				{
					content='<div class="form-group col-12 m-0 p-0">'+
							'<textarea class="form-control"  rows="3"></textarea> </div> '
					$(field).append(content);
				}
				else
				{
					var answers=data[i].question.answers;
	        		
	        		for (var j = 0; j < answers.length; j++) {
	        			content=String(answers[j].content);
	        			//remove p tag
	    				content=content.substring(3);
	    				//add patag with index question
	    				content='<div class="m-0 p-0" style="width:50%;>"<p><b>'+index[j]+'&nbsp.</b>&nbsp'+content+'</div>';
						$(field).append(content);
					}
				}
				
        	
        	}
    	}
    	else
    	{
    		alert('Lỗi Xảy Ra Hoặc Bài Thi Không Có Nội Dung !');
    	}
    	
    	
    }
}