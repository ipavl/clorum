// Loop through each post element
$('.post').each(function() {
  var verifiedLabel = $(this).find('p.post-verified');

  // Prettify post verification indicators
  if (verifiedLabel.text() === "(verified: 1)") {
    $(this).find("p.post-author").addClass("is-verified");
  } else {
    $(this).find("p.post-author").addClass("is-guest");
  }

  verifiedLabel.hide();
});
