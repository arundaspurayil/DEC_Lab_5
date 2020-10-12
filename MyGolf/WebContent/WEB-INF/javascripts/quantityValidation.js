$(document).ready(function() {
	$("#order-entry-errors").hide()
    $("#order-submit-button").click(function(event) {
    	event.preventDefault()
    	var golfBallsQuantity = $("#Golf\\ Balls").val()
        var wedgeQuantity = $("#Wedge").val()
        var driverQuantity = $("#Driver").val()
        var golfShoesQuantity = $("#Golf\\ Shoes").val()
        var golfGlovesQuantity = $("#Golf\\ Gloves").val()
        $.ajax({
            type: "GET",
            url: "/MyGolf/quantity",
            data: {
            	golfBallsQuantity, 
            	wedgeQuantity, 
            	driverQuantity, 
            	golfShoesQuantity, 
            	golfGlovesQuantity
            },
            success : function(data) {
            	if(data == ""){
            		$("#order-entry-form").submit()
            	}else{
            		$("#order-entry-errors").text(data)
                	$("#order-entry-errors").show()
            	}
            	
            }
        });
    });     
});  