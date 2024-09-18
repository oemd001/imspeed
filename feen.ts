.legend-label {
  position: relative;
  cursor: pointer;
}

.legend-label:hover::after {
  content: attr(data-title);
  position: absolute;
  left: 0;
  top: 100%;
  background-color: rgba(0, 0, 0, 0.75);
  color: white;
  padding: 5px;
  border-radius: 3px;
  white-space: nowrap;
}
