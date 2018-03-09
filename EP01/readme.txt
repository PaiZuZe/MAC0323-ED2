/******************************************************************************
 *  Name: Victor Chiaradia Gramuglia Araujo
 *  NetID:
 *  Precept:
 *
 *  Partner Name:
 *  Partner NetID:
 *  Partner Precept:
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 2: Deques and Randomized Queues


/******************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 *****************************************************************************/
Para possuir tempo de remoção e adição constante foi usada uma lista duplamente ligada que mantinha o primeiro e  o último Node na implementação do Deque. Outros detalhes da implementação são simples.

Um vetor dinâmico foi usado para implementar o Randomized Queue, pois navegar até o item que seria retirado é constante para um vetor e colocando o último elemento na nova posição vazia evita-se a criação de buracos no vetor. A última posição preenchida é guardada para auxiliar na na inserção de itens.



/******************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 *****************************************************************************/
 Randomized Queue:   ~  32 bytes
 16 bytes do Object overhead + 8 bytes de dois ints + x*n de memória usada pelo vetor
 Deque:              ~  20 + 16n  bytes

 16 bytes para duas referências do primeiro e o ultimo Node + 4 bytes de um int + 16*n bytes de referência para o próximo Node e o anterior + 16 bytes para o objeto + x*n de memória usada pelos items



 /******************************************************************************
  *  Known bugs / limitations.
  *****************************************************************************/


 /******************************************************************************
  *  Describe whatever help (if any) that you received.
  *  Don't include readings, lectures, and precepts, but do
  *  include any help from people (including course staff, lab TAs,
  *  classmates, and friends) and attribute them by name.
  *****************************************************************************/



 /******************************************************************************
  *  Describe any serious problems you encountered.
  *****************************************************************************/



 /******************************************************************************
  *  List any other comments here. Feel free to provide any feedback
  *  on how much you learned from doing the assignment, and whether
  *  you enjoyed doing it.
  *****************************************************************************/
