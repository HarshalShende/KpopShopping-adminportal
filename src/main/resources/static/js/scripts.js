/**
 * 
 */

$(document).ready(function() {
	$('.delete-cd').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this cd? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	var cdIdList=[];
	
	$('.checkboxCd').click(function() {
		var id=$(this).attr('id');
		if(this.checked){
			cdIdList.push(id);
		} else {
			cdIdList.splice(cdIdList.indexOf(id), 1);
		}
	})
	
	$('#deleteSelected').click(function() {
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected cds? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(cdIdList),
						contentType: "application/json",
						success: function(res) {console.log(res); location.reload()},
						error: function(res){console.log(res); location.reload();}
					});
				}
			}
		});
	});
	
	$("selectAllCds").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkBox").click();
		} else if ($(this).prop("checked")==false) {
			$(".checkBox").click();
		}
	})
});