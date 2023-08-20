const FormattedDate = ({ dateString }) => {
  const originalDate = new Date(dateString);
  const formattedDate = originalDate.toLocaleString('ko-KR', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  });

  return (
    <div>{formattedDate}</div>
  );
};

export default FormattedDate;
