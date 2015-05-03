// Loop through each post element
$('.post').each(function() {
  var verifiedLabel = $(this).find('p.post-verified');

  // Prettify post verification indicators
  if (verifiedLabel.text() === "(verified: 1)") {
    $(this).addClass("is-verified");

    verifiedLabel.html("Verified");
  } else {
    verifiedLabel.html("");
  }
});
