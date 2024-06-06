$(document).ready(function() {
  var apiUrl = 'https://fakestoreapi.com/products';

  
  function getProducts(searchTerm) {
    var searchUrl = apiUrl;
    if (searchTerm) {
      searchUrl += '/?title=' + searchTerm;
    }

    $.get(searchUrl, function(products) {
      // Limpar a lista de produtos antes de adicionar os resultados da pesquisa
      $('#product-list').empty();

     
      $.each(products, function(index, product) {
        // Criar os elementos HTML para exibir os dados do produto
        var listItem = $('<div>').addClass('product-item');
        var productName = $('<h3>').text(product.title);
        var productImage = $('<img>').attr('src', product.image);
        var productDescription = $('<p>').text(product.description);
        var detailsLink = $('<a>').attr('href', 'info.html?id=' + product.id).text('Detalhes');

       
        listItem.append(productName, productImage, productDescription, detailsLink);
        $('#product-list').append(listItem);
      });
    });
  }

  
  $('#search-form').submit(function(event) {
    event.preventDefault();
    var searchTerm = $('#search-input').val();
    getProducts(searchTerm);
  });

  
  getProducts();
});
