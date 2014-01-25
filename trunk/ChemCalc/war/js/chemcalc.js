function clearFields() {
	$("#result").val("");
	$("#instance").val("");
	$("#message").val("");
}

function calculate() {
	if (($("#formula").val() != null) && ($("#formula").val() != "")) {
	    $("#message").removeAttr("disabled");
	    $("#instance").removeAttr("disabled");
	    $("#chemcalc").submit();
	}
}

function setAction(action) {
	if (action === "Calculate") {
		calculate();
	} else {
		if (action === "Clear") {
			$("#formula").val("");
			clearFields();	
		} else {
            var chemform = $("#formula").val();
			if (action === "Backspace") {
			    $("#formula").val(chemform.substring(0, chemform.length - 1));
			} else {
				$("#formula").val(chemform + action);
				clearFields();
		    }
		}
	}
}

function calcExample(example) {
	$("#formula").val(example);
	calculate();
}

$(document).keyup(function(event) {
    if (event.keyCode == 13) {
    	if (($("#formula").val() != null) && ($("#formula").val() != "")) {
    	    calculate();
    	}
    }
});

$(document).ready(function() {
	$("td").click(function() {
		setAction($(this).text());
	});
	$("select").change(function() {
		calcExample($("#examples option:selected").text());
	});
});