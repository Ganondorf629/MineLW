(function() {

  let xhr = new XMLHttpRequest();
  let currentPage;

  document.body.onload = function() {
    document.getElementById('loading').remove();
  }

  document.getElementById('navigation-list').addEventListener('click', function(e) {
    let li = e.target;
    if (li.tagName === 'LI' && li.classList.contains('navigation-link')) {
      let newPage = li.dataset.page + ".html"; // A CHANGER ====================

      if(currentPage != newPage) {
        ProgressBar.show();

        xhr.open('GET', newPage);

        xhr.onprogress = function(e) {
          if(e.lengthComputable)
            ProgressBar.setPercentage((e.loaded / e.total) * 100);
        };

        xhr.onload = function() {
          let responseDoc = new DOMParser().parseFromString(xhr.responseText, 'text/html');
          let title = responseDoc.title;
          document.title = title;
          document.getElementById('content-container').innerHTML = responseDoc.getElementById('content-container').innerHTML;
          history.pushState(null, title, newPage);
          ProgressBar.hide();
          currentPage = newPage;
        };

        xhr.onerror = function(e) {
          Notifier.error('&#xE000;', '%{error.loading}', e.statusText);
          ProgressBar.hide();
        };

        xhr.send(null);
      }
    }
  });
})();
