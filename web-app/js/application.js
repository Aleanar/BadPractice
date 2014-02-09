if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

$(document).ready(function() {
    $('#thread-editor').summernote({
        height: '300'
    });
    $('#post-editor').summernote();
    $('#comment-editor').summernote({
        height: '100',
        toolbar: [
            ['style', ['bold', 'italic', 'underline', 'clear']]
        ]
    });
});