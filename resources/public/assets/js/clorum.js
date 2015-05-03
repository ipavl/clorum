// Prettify post verification indicators
$('.post-verified').each(function() {
  if ($(this).text() === "(verified: 1)") {
    $(this).html("Verified");
  } else {
    $(this).html("");
  }
});
