function openTab(evt, cityName, firstButtonId, secondButtonId) {
    // Declare all variables
    var i, tabcontent, tablinks;

    //Set required=false to all fields
    document.getElementById('textField').required = false;
    document.getElementById('textField').value = '';
    document.getElementById('urlField').required = false;
    document.getElementById('urlField').value = '';
    document.getElementById('smsPhoneField').required = false;
    document.getElementById('smsPhoneField').value = '';
    document.getElementById('smsMessageField').required = false;
    document.getElementById('smsMessageField').value = '';
    document.getElementById('phoneField').required = false;
    document.getElementById('phoneField').value = '';

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="nav-link" and remove the class "active"
    tablinks = document.getElementsByClassName("nav-link");
    for (i = 0 ; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the link that opened the tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";

    document.getElementById(firstButtonId).required = true;

    if (secondButtonId !== '') {
        document.getElementById(secondButtonId).required = true;
    }
}